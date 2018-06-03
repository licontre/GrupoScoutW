package BackingBeans;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.Part;
import modeloJPA.Documento;
import negocio.Documentos.GestionDocs;
import negocio.Documentos.GestionDocsImpl;
import negocio.InfoSession.InfoSessionImpl;



/**
 *
 * @author PC
 */
@ManagedBean(name = "manejadorDocumentos", eager = true)
@SessionScoped
public class ManejadorDocumentos implements Serializable {

    private List<Documento> documentos;
    private String nombreDocumento;
    private String tipo;
    private String estado;
    private Documento documento;
    private Part fich;
    private byte [] archivo;
    private int longArch;

    private byte [] copiadocumento;

    public byte[] getCopiadocumento() {
        return copiadocumento;
    }

    public void setCopiadocumento(byte[] copiadocumento) {
        this.copiadocumento = copiadocumento;
    }
    
    @Inject
    private GestionDocs gest;
    
    @Inject
    private InfoSessionImpl info;
   
    
    public ManejadorDocumentos(){
        gest = new GestionDocsImpl();
        documento = new Documento();
       // info = new InfoSessionImpl();
    }

    /**
     * @return the documentos
     */


    public String subir() throws IOException {
       try (InputStream input = fich.getInputStream()) {
            String ruta = System.getProperty("user.dir");
            System.out.println("---------------"+ruta);
            
            File directorio = new File (ruta+"\\DocsScouts");
            
            directorio.mkdir();
            
            Files.copy(input, new File(ruta+"\\DocsScouts\\"+getFileName(fich)).toPath());
            
            System.out.println("El objeto file");
            longArch = (int)fich.getSize();
            
            if(longArch>0){
               archivo = new byte[longArch];
               System.out.println("longitud del archivo:"+longArch);
               try(DataInputStream f = new DataInputStream(fich.getInputStream())){
                   System.out.println("----------DATA INPUT"+f);
                   f.readFully(archivo);
               }
               //documento.setCopiadocumento(archivo);
                setCopiadocumento(archivo);
            }
            
            return "documentacion.xhtml";
            
        }catch(NoSuchFileException e){
            
        }
        return null;
    }
    
    
    
    public String getFileName(Part part)
    {
        for(String cd:part.getHeader("content-disposition").split(";"))
            if(cd.trim().startsWith("filename")){
                String filename=cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
                return filename;
            }
        return "";
                
    }
      
    public String modificar(){
        gest.modificarDocumento(documento);
        return "documentacion.xhtml";
    }
    
     public String eliminar(){
        gest.eliminarDocumento(documento);
        return "documentacion.xhtml";
    }
     
    public String anadirDoc(){
        Documento aux = new Documento();
        aux.setNombre(nombreDocumento);
        aux.setEstado(estado);
        aux.setTipo(tipo);
        aux.setCopiadocumento(copiadocumento);
        Long id = info.getUser().getId();
        gest.guardarDocumento(aux,id);
        return "documentacion.xhtml";
    }
    
    
   
    
    public void descargarDoc()throws IOException{
        Documento d = gest.descargarDoc(documento.getId());
        byte[] docum =d.getCopiadocumento();
        
        int longitudArchivo = docum.length;
        System.out.println(longitudArchivo+"------------");
        
        FacesContext fcontext = FacesContext.getCurrentInstance();
        ExternalContext ec = fcontext.getExternalContext();
        
        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseContentLength(longitudArchivo);
        ec.setResponseHeader("Content-Disposition","attachment; filename=\""+documento.getNombre()+"\"");
        
        OutputStream salida = ec.getResponseOutputStream();
        if(longitudArchivo>0)salida.write(docum);
        salida.flush();
        salida.close();
        fcontext.responseComplete();
    }
    
    public List<Documento> getDocumentos() {
    //documentos = em.createQuery("SELECT d FROM Documento d").getResultList();
    //return gest.devolverDoc();
        if (info.getUser().getLista().getId() == 6 || info.getUser().getLista().getId() == 7) {
            return gest.devolverDoc();
        } else {
            return gest.usuarioDocumentos(info.getUser().getId());
        }
        
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * @return the documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * @return the fich
     */
    public Part getFich() {
        return fich;
    }

    /**
     * @param fich the fich to set
     */
    public void setFich(Part fich) {
        this.fich = fich;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

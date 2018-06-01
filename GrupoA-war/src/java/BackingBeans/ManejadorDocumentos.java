package BackingBeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;


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

    @Inject
    private GestionDocs gest;
    
    @Inject
    private InfoSessionImpl info;
   
    
    public ManejadorDocumentos(){}

    /**
     * @return the documentos
     */
    public List<Documento> getDocumentos() {
        //documentos = em.createQuery("SELECT d FROM Documento d").getResultList();
        //return gest.devolverDoc();
        return gest.devolverDoc();
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

    public void subir() throws IOException {
        try (InputStream input = fich.getInputStream()) {
            Files.copy(input, new File("C:/Users/PC/Documents/doc.pdf").toPath());
        }
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
    public void upload()
    {
        try{
        fich.write("C:/Users/PC/Documents/"+getFileName(fich));
        }
        catch(Exception ex)
        {
            System.err.print(ex);
        }
    }
    
    public String modificar(){
        gest.modificarDocumento(documento);
        return "documentacion.xhtml";
    }
    
     public void eliminar(){
        gest.eliminarDocumento(documento);
    }
     
    public String anadirDoc(){
        Documento aux = new Documento();
        aux.setNombre(nombreDocumento);
        aux.setEstado(estado);
        aux.setTipo(tipo);
        Long id = info.getUser().getId();
        gest.guardarDocumento(aux,id);
        return "documentacion.xhtml";
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

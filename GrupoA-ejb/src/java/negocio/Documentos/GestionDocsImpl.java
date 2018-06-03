/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Documentos;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Documento;
import modeloJPA.Usuario;

/**
 *
 * @author gutir
 */
@Stateless
@LocalBean
public class GestionDocsImpl implements GestionDocs {

    private Documento documento;
    private Usuario usuario;

    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;
    
    

    @Override
    public void guardarDocumento(Documento doc,Long idUser) {
        Usuario u = em.find(Usuario.class, idUser);
        doc.setUsuario(u);
        em.persist(doc);
        
    }

    @Override
    public void modificarDocumento(Documento doc) {

        // doc.setId(documento.getId());
        documento = doc;
        em.merge(doc);

        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void eliminarDocumento(Documento doc) {
        //Query consulta = em.createNamedQuery("VerDoc",Documento.class);
        //consulta.setParameter("id", doc.getId());
        Documento borra = em.find(Documento.class, doc.getId());
        if (borra != null) {
            if (!em.contains(borra)) {
                doc = em.merge(borra);
            }
            em.remove(borra);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Documento> devolverDoc() {

        List<Documento> ld = em.createQuery("SELECT d FROM Documento d").getResultList();
        // List<Documento> ld = null;
        //return ld;
        return ld;

    }
    
    public List<Documento> usuarioDocumentos(Long idUsuario) {
       
       /* List<Documento> ld = em.createQuery("SELECT d FROM Documento d").getResultList();
        List<Documento> dcs = new ArrayList<Documento>();
        for(Documento d : ld){
            if(d.getUsuario().getId()== idUsuario){
                
            }
        }*/
       Query q = em.createNamedQuery("documentoUsuario");
        q.setParameter("idUser", idUsuario);
        return q.getResultList();
    }

    @Override
    public Documento descargarDoc(Long idDocumento) {
         Documento descargar = em.find(Documento.class, idDocumento);
          return descargar;      
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

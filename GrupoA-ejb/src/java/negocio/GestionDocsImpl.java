/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
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

    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;

    @Override
    public void guardarDocumento(Documento doc) {
       // EntityTransaction tx = em.getTransaction();
        //tx.begin();
        em.persist(doc);
        //tx.commit();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarDocumento(Documento doc) {

        // doc.setId(documento.getId());
        documento = doc;
        em.merge(doc);

        /* Query consulta = em.createNamedQuery("VerCorreo",Usuario.class);
        consulta.setParameter("email", usu.getEmail());
        List <Usuario> usuarios = consulta.getResultList();
        if(usu.getEmail()!=user.getEmail()&&usuarios != null){//Correo en uso
            return null;
            //throw new CuentaRepetidaException("Correo electronico en uso");
        }
        usu.setId(user.getId());
        user = usu;
        em.merge(usu);
        return getHome();
         */
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}

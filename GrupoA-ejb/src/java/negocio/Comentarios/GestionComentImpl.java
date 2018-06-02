/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Comentarios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Comentario;
import modeloJPA.Evento;
import modeloJPA.Usuario;

/**
 *
 * @author root
 */
@Stateless
@LocalBean
public class GestionComentImpl implements GestionComent {

    
    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;
    private Evento evento;
    private Usuario user;
    private List<Comentario>comentarios;
    
    @Override
    public void comentar(Comentario coment){
        em.persist(coment);
    }
    
    
    @Override
    public List<Comentario> getComentarios() {
        Query cons = em.createNamedQuery("VerComentarios",Comentario.class);
        comentarios = cons.getResultList();
        if (comentarios == null){
            comentarios = new ArrayList<>();
        }
        return comentarios;
    }
    

    @Override
    public List<Comentario> getComentariosEvento(Evento ev) {
        List<Comentario> coments=new ArrayList<>();
        this.getComentarios();
        for(Comentario co : comentarios){
            if(ev.equals(co.getComentarios())){
                coments.add(co);
            }
        }
        return coments;
    }

    @Override
    public void setComentario(Evento ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the user
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Usuario user) {
        this.user = user;
    }
    
    @Override
    public void borrar(Comentario coment){
        Comentario borra = em.find(Comentario.class, coment.getId());
        if (borra != null) {
            if (!em.contains(coment)) {
                coment = em.merge(coment);
            }
            em.remove(coment);
            System.out.println("BORRADO");
        } else {
            System.out.println("NO HA BORRADO");
        } 
    }   
    
}

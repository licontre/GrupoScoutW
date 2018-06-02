/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Eventos;

import java.util.List;
//import static javafx.scene.input.KeyCode.T;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Evento;
import modeloJPA.Seccion;

/**
 *
 * @author PC
 */
@Stateless
public class Eventos {

    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;

    public void eliminar(Evento ev) {
        Evento borra = em.find(Evento.class, ev.getId());
        if (borra != null) {
            if (!em.contains(ev)) {
                ev = em.merge(ev);
            }
            em.remove(ev);
            System.out.println("BORRADO");
        } else {
            System.out.println("NO HA BORRADO");
        }
    }

    public void annadir(Evento ev, int idSeccion) {

        Long l = new Long(idSeccion);
        Seccion sec = em.find(Seccion.class, l);
        ev.setSeccion(sec);
        em.persist(ev);

    }
    
    public void modificar(Evento ev){
        em.merge(ev);
    }

    public List<Evento> todosEventos() {
        Query q = em.createNamedQuery("findAll");
        return q.getResultList();
    }
    
    public List<Evento> SeccionEventos(Long idUsuario) {
        Query q = em.createNamedQuery("eventoSeccion");
        q.setParameter("idUser", idUsuario);
        return q.getResultList();
    }

}

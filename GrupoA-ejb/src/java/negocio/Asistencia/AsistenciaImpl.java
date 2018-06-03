/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Asistencia;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Asistencia;
import modeloJPA.Evento;
import modeloJPA.Usuario;

/**
 *
 * @author PC
 */
@Stateless
@LocalBean
public class AsistenciaImpl {
    
    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;
    
    public void annadir(Asistencia asis){
        em.persist(asis);
    }
    
    public void borrar(Evento event,Usuario user){
        Query q = em.createNamedQuery("apuntado");
        q.setParameter("idEvento", event.getId());
        q.setParameter("idUser", user.getId());
        Asistencia asis = (Asistencia)q.getResultList().get(0);
        Asistencia borra = em.find(Asistencia.class, asis.getId());
        if (borra != null) {
            if (!em.contains(asis)) {
                asis = em.merge(asis);
            }
            em.remove(asis);
            System.out.println("BORRADO");
        } else {
            System.out.println("NO HA BORRADO");
        }
    }
    
    public boolean ispuntado(Evento event, Usuario user){
        boolean esta=true;
        try{
        Query q = em.createNamedQuery("apuntado");
        q.setParameter("idEvento", event.getId());
        q.setParameter("idUser", user.getId());
        Asistencia asis =(Asistencia)q.getSingleResult();

        }catch (NoResultException e1){
            esta=false;
        }catch(NonUniqueResultException e2){
            esta=false;
        }
        return esta;
    }
    
    public List<Usuario> usuariosApuntados(Evento event){
        Query q = em.createNamedQuery("listaApuntados");
        q.setParameter("idEvento", event.getId());
        return q.getResultList();
    }
    
    public String dameObservacion(Long idUser,Long idEvent){
        
        String obs="";
        try{
        Query q = em.createNamedQuery("observacion");
        q.setParameter("idEvent", idEvent);
        q.setParameter("idUser", idUser);
        obs = (String) q.getSingleResult();

        }catch (NoResultException e1){
            obs="-";
        }catch(NonUniqueResultException e2){
            
        }
        return obs;
    }
}

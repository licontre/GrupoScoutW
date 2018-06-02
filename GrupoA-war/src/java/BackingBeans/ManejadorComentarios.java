/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import modeloJPA.Comentario;
import modeloJPA.Evento;
import negocio.Comentarios.GestionComentImpl;
import negocio.InfoSession.InfoSessionImpl;

/**
 *
 * @author PC
 */
@ManagedBean(name="manejadorComentarios", eager=true)
@SessionScoped
public class ManejadorComentarios {
    @Inject
    private InfoSessionImpl session;
    @Inject
    private  GestionComentImpl gestorComent;
    private List<Comentario> comentarios;
    private String comment;
    private Evento eventos;
    
    public ManejadorComentarios(){
        comentarios=new ArrayList<>();
    }
    
    public List<Comentario> getComentarios(){
        return comentarios;
    }
    public List<Comentario> getComentarios(Evento ev){
        return getGestorComent().getComentariosEvento(ev);
    }
    
    public void addComentarios(Evento e){
        this.eventos = e;
        this.gestorComent.comentar(new Comentario(new Date(),comment,session.getUser(),e));
    }
    
    public String getComment(){
        return comment;
    }
    
   public void setComment(String comment){
       this.comment=comment;
   }

    /**
     * @return the gestorComent
     */
    public GestionComentImpl getGestorComent() {
        return gestorComent;
    }

    /**
     * @param gestorComent the gestorComent to set
     */
    public void setGestorComent(GestionComentImpl gestorComent) {
        this.gestorComent = gestorComent;
    }

    /**
     * @return the eventos
     */
    public Evento getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(Evento eventos) {
        this.eventos = eventos;
    }
    
    public void borralo(Comentario c){
        this.gestorComent.borrar(c);
    }
    
}

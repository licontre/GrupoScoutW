/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import modeloJPA.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modeloJPA.Asistencia;
import modeloJPA.Evento;
import negocio.Asistencia.AsistenciaImpl;
import negocio.InfoSession.InfoSession;



@Named(value = "controlAsistencia")
@SessionScoped
public class ControlAsistencia implements Serializable{
    
    private List<Usuario> usuarios;
    private String observacion;
    
    @Inject
    private InfoSession info;
    
    @Inject
    private AsistenciaImpl asisEJB;
    
    public ControlAsistencia(){}

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public String apuntarse(Evento event){
        Asistencia asist = new Asistencia();
        asist.setEvento(event);
        asist.setUsuario(info.getUser());
        asist.setObservacion(observacion);        
        asisEJB.annadir(asist);
        return "verEvento.xhtml";
        
    }
    
    public void borrarse(Evento event){
        asisEJB.borrar(event,info.getUser());
    }
    
    public boolean isApuntado(Evento event){ 
        return asisEJB.ispuntado(event, info.getUser());
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

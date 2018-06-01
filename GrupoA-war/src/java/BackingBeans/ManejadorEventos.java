/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import modeloJPA.Evento;
import negocio.Eventos;

/**
 *
 * @author PC
 */
@ManagedBean(name = "manejadorEventos", eager = true)
@SessionScoped
public class ManejadorEventos implements Serializable {

    private List<Evento> eventos;
    private String nombreEvento;
    private Evento evento;
    private Date fecha;
    private String loc;
    private float precio, pres;
    private String dsc;

    private int idSeccion;

    @Inject
    private Eventos eventEJB;

    public ManejadorEventos() {

    }

    public void eliminarEvento(Evento event) {
        eventEJB.eliminar(event);
    }

    public void crearEvento() {
        Evento aux = new Evento();
        aux.setNombre(nombreEvento);
        aux.setFecha(fecha);
        aux.setDescripcion(dsc);
        aux.setLocalizacion(loc);
        aux.setPrecio(precio);
        aux.setPresupuesto(pres);
        eventEJB.annadir(aux, idSeccion);
    }

    public void modificarEvento(Evento event) {
        eventEJB.moficar(event);
    }

    public List<Evento> getEventos() {
        return eventEJB.todosEventos();
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
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
     * @return the loc
     */
    public String getLoc() {
        return loc;
    }

    /**
     * @param loc the loc to set
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the pres
     */
    public float getPres() {
        return pres;
    }

    /**
     * @param pres the pres to set
     */
    public void setPres(float pres) {
        this.pres = pres;
    }

    /**
     * @return the dsc
     */
    public String getDsc() {
        return dsc;
    }

    /**
     * @param dsc the dsc to set
     */
    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idSeccion
     */
    public int getIdSeccion() {
        return idSeccion;
    }

    /**
     * @param idSeccion the idSeccion to set
     */
    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PC
 */
@ManagedBean(name = "manejadorSecciones", eager = true)
@SessionScoped
public class ManejadorSecciones {

    private int id;
    private String nombre;
    private int edadminima;
    private int edadmaxima;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edadminima
     */
    public int getEdadminima() {
        return edadminima;
    }

    /**
     * @param edadminima the edadminima to set
     */
    public void setEdadminima(int edadminima) {
        this.edadminima = edadminima;
    }

    /**
     * @return the edadmaxima
     */
    public int getEdadmaxima() {
        return edadmaxima;
    }

    /**
     * @param edadmaxima the edadmaxima to set
     */
    public void setEdadmaxima(int edadmaxima) {
        this.edadmaxima = edadmaxima;
    }
}

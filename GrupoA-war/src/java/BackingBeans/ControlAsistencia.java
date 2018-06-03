/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ControlAsistencia implements Serializable {

    private List<Usuario> usuarios;
    private String observacion;
    private String path;

    @Inject
    private InfoSession info;

    @Inject
    private AsistenciaImpl asisEJB;
    
    

    public ControlAsistencia() {
    }

    public List<Usuario> dameUsuarios(Evento event) {
        return asisEJB.usuariosApuntados(event);
    }

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

    public String apuntarse(Evento event) {
        Asistencia asist = new Asistencia();
        asist.setEvento(event);
        asist.setUsuario(info.getUser());
        asist.setObservacion(observacion);
        asisEJB.annadir(asist);
        return "verEvento.xhtml";

    }

    public String dameObservacion(Usuario u, Evento event) {
        return asisEJB.dameObservacion(u.getId(), event.getId());
    }

    public String listaCSV(Evento event) throws IOException {
        List <Usuario> aux = asisEJB.usuariosApuntados(event);
        path=System.getProperty("user.dir")+"\\"+event.getNombre().replaceAll(" ", "_")+"_lista.csv";
        try {
            
            File fich = new File(path);
            System.out.println(fich.getAbsolutePath());
            FileWriter fw = new FileWriter(fich, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.println("Nombre;Apellidos;Observacion");
                aux.forEach((u) -> {
                    pw.println(u.getNombre() + ";" + u.getApellidos() + ";" + asisEJB.dameObservacion(u.getId(), event.getId()));
                });
                pw.flush();
            }
            
        } catch (Exception e) {}
        return null;
    }

    public void borrarse(Evento event) {
        asisEJB.borrar(event, info.getUser());
    }

    public boolean isApuntado(Evento event) {
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

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}

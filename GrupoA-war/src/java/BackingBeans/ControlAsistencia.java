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
import javax.inject.Named;



@Named(value = "controlAsistencia")
@SessionScoped
public class ControlAsistencia implements Serializable{
    
    private List<Usuario> usuarios;
    
    public ControlAsistencia(){
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Juanito","Velasco"));
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Alba","Perez"));
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Pablo","Paredes"));
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Luis","Garcia"));
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Maria","Torres"));
        usuarios.add(new Usuario("castor", "castor", Usuario.Cargo.CASTORES,"Julia","Dominguez"));
        usuarios.add(new Usuario("monitor", "monitor", Usuario.Cargo.MONITOR,"Caroline","Ho"));
        usuarios.add(new Usuario("admin", "admin", Usuario.Cargo.MONITOR,"Luis","Castillo"));
        usuarios.add(new Usuario("secretaria", "secretaria", Usuario.Cargo.SECRETARIA,"Pepe","Wilfred"));
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
    
    public void apuntarse(Usuario user){
        usuarios.add(user);
    }
}

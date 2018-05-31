/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Seccion;
import modeloJPA.Usuario;

/**
 *
 * @author licontrex
 */
@Stateful
public class EditarUsuarioImpl implements EditarUsuario{
    
    private Session session;
    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;
    private Usuario user;
    private List<Usuario> usuarios;

    @Override
    public List<Usuario> getUsuarios() {
        Query consulta = em.createNamedQuery("VerUsuarios",Usuario.class);
        this.usuarios = consulta.getResultList();
        return usuarios;        
    }
    
     @Override
    public String modificarDatosUsuario(Usuario usu,String cargo) throws CuentaRepetidaException{
        
        Query consulta = em.createNamedQuery("VerCorreo",Usuario.class);
        if(!usu.getEmail().equals(this.user.getEmail())){
            consulta.setParameter("email", usu.getEmail());
            List <Usuario> usua = consulta.getResultList();
            for (Usuario u:usua){
                return null;
            }
        }
        Query sec = em.createNamedQuery("VerSecciones",Seccion.class);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<Seccion> seccion = sec.getResultList();
        int i = 0;
        if(seccion!=null && cargo!=null){
            while(i<seccion.size()&&!seccion.get(i).getNombre().equals(cargo)){
                i++;
            }
            if(i<seccion.size()){
                usu.setLista(seccion.get(i));
            }
        }
        em.merge(usu);
        return "miembros.xhtml";
    }

    @Override
    public Usuario getUser() {
        return user;
    }

    @Override
    public void setUser(Usuario us) {
        this.user = us;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Pagos;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeloJPA.Cuota;
import modeloJPA.PagoCuota;
import modeloJPA.Usuario;

/**
 *
 * @author MARTA
 */
@Stateless
@LocalBean
public class GestPagosImpl implements GestPagos{

    private List<Usuario> usuarios;
    
    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;
    
    @Override
    public void guardarPago(String correo, Cuota cuota, PagoCuota pagCuota) {
        
       /* Query q = em.createNamedQuery("VerIdCorreo");
        
        
        Query consulta = em.createNamedQuery("VerIdCorreo",Usuario.class);
        this.usuarios = consulta.setParameter("uCorreo",correo).getResultList();
        
        pagCuota.setUsuario(usuarios.get(0));     
        
        */
       
        Query cons = em.createNamedQuery("VerCorreo",Usuario.class);
        cons.setParameter("email",correo);
        List<Usuario> lis = cons.getResultList();
        if(!lis.isEmpty()){
            for(Usuario u : lis){
                pagCuota.setUsuario(u);
                em.persist(cuota);
               // pagCuota.setPagoscuota(cuota);
                //em.persist(pagCuota);
            }
        }
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void eliminarCuota(Cuota c) {
         em.remove(c);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

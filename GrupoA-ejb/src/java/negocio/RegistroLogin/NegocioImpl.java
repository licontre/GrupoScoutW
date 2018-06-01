/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.RegistroLogin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import modeloJPA.Seccion;
import modeloJPA.Usuario;

/**
 *
 * @author francis
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static final int TAM_CADENA_VALIDACION = 20;

    //@Resource(name = "mail/agenda")
    private Session session;

    @PersistenceContext(unitName = "GrupoA-ejbPU")
    private EntityManager em;


    @Override
    public void registrarUsuario(Usuario u) throws RegistroException{
    
        Query cons = em.createNamedQuery("VerCorreo",Usuario.class);
        cons.setParameter("email",u.getEmail());
        List<Usuario> lis = cons.getResultList();
        if(lis!=null){
            for(Usuario user:lis){
                System.out.println("-------------------->"+user.getNombreusuario());
                throw new CuentaRepetidaException("Correo ya registrado.");
            }}
        Query sec = em.createNamedQuery("VerSecciones",Seccion.class);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("---------------------------"+edad(df.format(u.getFechanacimiento()))+"------------------------");
        List<Seccion> seccion = sec.getResultList();
        int i = 0;
        if(seccion!=null){
            while(i<seccion.size()&&edad(df.format(u.getFechanacimiento()))> seccion.get(i).getEdadmaxima()){
                System.out.println(seccion.get(i).getEdadmaxima());
                i++;
            }
        }
        u.setLista(seccion.get(i));
        em.persist(u);
        System.out.println("Registrado "+u.getNombreusuario()+" -> "+seccion.get(0).getNombre());
    }
    private int edad(String fecha_nac) {     //fecha_nac debe tener el formato dd/MM/yyyy
   
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String hoy = formato.format(fechaActual);
    String[] dat1 = fecha_nac.split("/");
    String[] dat2 = hoy.split("/");
    int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
    int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
    if (mes < 0) {
      anos = anos - 1;
    } else if (mes == 0) {
      int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
      if (dia > 0) {
        anos = anos - 1;
      }
    }
    return anos;
  }

    @Override
    public void compruebaLogin(Usuario u)throws RegistroException{
        Usuario user2 = em.find(Usuario.class, u.getId());
        if (user2 == null) {
           throw new CuentaInexistenteException("Cuenta no existe");
        }
        if (!user2.getContrasenia().equals(u.getContrasenia())) {
            throw new ContraseniaInvalidaException("Contraseña incorrecta");
        }
    }

   

    @Override
    public void validarCuenta(String cuenta, String validacion) throws RegistroException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

package BackingBeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import modeloJPA.Cuota;
import modeloJPA.PagoCuota;
import modeloJPA.Usuario;
import modeloJPA.Seccion.Cargo;
import negocio.Pagos.GestPagos;

/**
 *
 * @author MARTA
 */
@Named(value = "controlPagos")
@SessionScoped
public class ControlPagos implements Serializable {

    private Usuario usuario;
    private Cuota cuota;
    private PagoCuota pagCuota;
    private List <Cuota> listaCuotas;
    private List<PagoCuota> listaPagosCuota;
    private float importePagoCuota;
    
    private float importe;
    private String nombre;
    private String descripcion;
    private String correoUser;
    
    private String dia;

    public PagoCuota getPagCuota() {
        return pagCuota;
    }

    public void setPagCuota(PagoCuota pagCuota) {
        this.pagCuota = pagCuota;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public GestPagos getGestp() {
        return gestp;
    }

    public void setGestp(GestPagos gestp) {
        this.gestp = gestp;
    }
    private String mes;
    private String anio;
    
    @Inject
    private GestPagos gestp;

    public float getImportePagoCuota() {
        return importePagoCuota;
    }

    public void setImportePagoCuota(float importePagoCuota) {
        this.importePagoCuota = importePagoCuota;
    }

    public String getCorreoUser() {
        return correoUser;
    }

    public void setCorreoUser(String correoUser) {
        this.correoUser = correoUser;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PagoCuota> getListaPagosCuota() {
        return listaPagosCuota;
    }

    public void setListaPagosCuota(List<PagoCuota> listaPagosCuota) {
        this.listaPagosCuota = listaPagosCuota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public List<Cuota> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(List<Cuota> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }
    
    public ControlPagos() {
        
        List<PagoCuota> listac1= new ArrayList<PagoCuota>();
        List<PagoCuota> listac2= new ArrayList<PagoCuota>();
        
        Usuario u1 = new Usuario("castor", "castor","Lola","Martin");
        Usuario u2 = new Usuario ("admin", "admin","Roci","Casa");
        
        Cuota c1 = new Cuota (1L, 100, "primera cuota", "inscripcion",listac1);
        
        PagoCuota pago1 = new PagoCuota (11L, new Date(2017, 5 , 28),u1 , c1);
        PagoCuota pago2 = new PagoCuota (12L, new Date(2017, 9 , 28),u1 , c1);
        
        listac1.add(pago1);
        listac1.add(pago2);
        
        
        Cuota c2 = new Cuota (2L, 120, "segunda cuota", "inscripcion",listac2);
        
        PagoCuota pago3 = new PagoCuota (23L, new Date(2017, 5 , 30),u1 , c1);
        PagoCuota pago4 = new PagoCuota (24L, new Date(2017, 9 , 8),u1 , c1);
        
        listac2.add(pago3);
        listac2.add(pago4);
        
        listaCuotas = new ArrayList<Cuota>();
        listaCuotas.add(c1);
        listaCuotas.add(c2);
        listaPagosCuota = new ArrayList<PagoCuota>();
        listaPagosCuota.add(pago1);
        listaPagosCuota.add(pago2);
        listaPagosCuota.add(pago3);
        listaPagosCuota.add(pago4);
        
    }
    
    
    public Float importePago(Cuota cuota){
        float importe = 0;
        int aux = cuota.getCuotas().size();
        
        return cuota.getImporte()/aux;
        
    }
    
    
    public String subirPago() throws ParseException{
        
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date fe = sm.parse(anio+"-"+mes+"-"+dia);
        
        Cuota c = new Cuota();
        PagoCuota p = new PagoCuota();
        c.setDescripcion(descripcion);
        c.setNombre(nombre);
        c.setImporte(importe);
        p.setFecha(fe);
        gestp.guardarPago(correoUser, c, p);
        
        return "pagos.xhtml";
    }
    
      public String eliminar(){
        gestp.eliminarCuota(cuota);
        return "pagos.xhtml";
    }
    
}
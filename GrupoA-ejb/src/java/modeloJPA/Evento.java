 package modeloJPA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "findAll", query = "select e from Evento e"),
    @NamedQuery(name= "eventoSeccion", query=" select e from Evento e, Usuario u where u.lista.id = e.seccion.id and u.id = :idUser"),
    @NamedQuery(name = "misEventos", query = "select e from Evento e, Asistencia a where a.usuario.id = :idUser")
})
public class Evento implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String localizacion;
    private float precio;
    private float presupuesto;
    @Column(length = 20000)
    private String descripcion;
    @ManyToOne
    private Seccion seccion;
    @OneToMany(mappedBy = "comentarios")
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "evento")
    private List<Asistencia> asistencia;
    @OneToMany(mappedBy = "eventos")
    private List<Documento> documentos;

    public Evento() {
    }

    public Evento(Long id, String nombre, Date fecha, String localizacion, float precio, float presupuesto, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.precio = precio;
        this.presupuesto = presupuesto;
        this.descripcion = descripcion;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> d) {
        this.documentos = d;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> n) {
        this.comentarios = n;
    }

    public List<Asistencia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<Asistencia> n) {
        this.asistencia = n;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public Date getFecha() {
        return fecha;
    }

    public String fechaFormateada() {
        SimpleDateFormat dateFormatNew = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatNew.format(fecha);
    }

    public void setFecha(Date n) {
        this.fecha = n;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String n) {
        this.localizacion = n;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float n) {
        this.precio = n;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float n) {
        this.presupuesto = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoScouts.Evento[ id=" + getId() + " ]";
    }

    /**
     * @return the seccion
     */
    public Seccion getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

}

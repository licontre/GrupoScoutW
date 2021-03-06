package modeloJPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
  @NamedQuery(name= "documentoUsuario", query=" select d from Documento d join Usuario u on d.usuario.id = u.id where  d.usuario.id =:idUser or d.usuario.lista.id = 7 or d.usuario.id = 6")
})
public class Documento implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String estado;
    @Lob
    private byte [] copiadocumento;
    private String tipo;
    @ManyToOne
    private Evento eventos;
    @ManyToOne
    private Usuario usuario;

    public Documento() {
    }
    
    public Documento(Long id, String nombre, String estado,byte[] copia, String tipo){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.copiadocumento = copia;
        this.tipo = tipo;
    }
    
    public void setEventos(Evento e){
        this.eventos = e;
    }
    public Evento getEventos(){
        return eventos;
    }
        
    public String getNombre (){
        return nombre;
    }
    public void setNombre (String n){
        this.nombre = n;
    }
    
    public String getEstado (){
        return estado;
    }
    public void setEstado (String n){
        this.estado = n;
    }
    
    public byte[] getCopiadocumento (){
        return copiadocumento;
    }
    public void setCopiadocumento (byte[] copiadocumento){
        this.copiadocumento = copiadocumento;
    }
    
    public String getTipo (){
        return tipo;
    }
    public void setTipo (String n){
        this.tipo = n;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuarios) {
        this.usuario = usuarios;
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
        
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoScouts.Documento[ id=" + getId() + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Comentarios;

import java.util.List;
import modeloJPA.Comentario;
import modeloJPA.Evento;

/**
 *
 * @author root
 */
public interface GestionComent {
    public List<Comentario> getComentarios();
    public List<Comentario>getComentariosEvento(Evento ev);
    public void setComentario(Evento ev);
    public void comentar(Comentario coment);
    public void borrar(Comentario coment);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Documentos;

import java.util.List;
import javax.ejb.Local;
import modeloJPA.Documento;

/**
 *
 * @author gutir
 */
@Local
public interface GestionDocs {
    public void guardarDocumento(Documento doc,Long idUser);
    public void modificarDocumento(Documento doc);
    public void eliminarDocumento(Documento doc);
    public List<Documento> devolverDoc();
    public List<Documento> usuarioDocumentos(Long idUsuario);
    public Documento descargarDoc(Long idDoc);
}

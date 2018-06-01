/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import javax.ejb.Local;
import modeloJPA.Documento;

/**
 *
 * @author gutir
 */
@Local
public interface GestionDocs {
    public void guardarDocumento(Documento doc);
    public void modificarDocumento(Documento doc);
    public void eliminarDocumento(Documento doc);
    public List<Documento> devolverDoc();
}
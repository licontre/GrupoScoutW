/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.Pagos;

import javax.ejb.Local;
import modeloJPA.Cuota;
import modeloJPA.PagoCuota;

/**
 *
 * @author MARTA
 */
@Local
public interface GestPagos {
    public void guardarPago(String correo, Cuota c, PagoCuota p);
    public void eliminarCuota(Cuota c);
}

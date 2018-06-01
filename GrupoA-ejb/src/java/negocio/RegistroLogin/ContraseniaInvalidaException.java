
package negocio.RegistroLogin;

/**
 *
 * @author francis
 */
public class ContraseniaInvalidaException extends RegistroException {
    
    public ContraseniaInvalidaException(String s){
        super(s);
    }
    public ContraseniaInvalidaException(){
        super();
    }
}

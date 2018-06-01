
package negocio.RegistroLogin;

import negocio.InfoSession.InfoSessionException;

/**
 *
 * @author francis
 */
public class ContrasenaIncorrectaException extends InfoSessionException {
    
    public ContrasenaIncorrectaException(){
        super();
    }
    public ContrasenaIncorrectaException(String m){
        super(m);
    }
}

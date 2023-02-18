package Pilas;
/**
 *
 * @author Retama
 */
public class ExcepcionColeccionVacia extends RuntimeException{

    public ExcepcionColeccionVacia() {
    }
    public ExcepcionColeccionVacia(String mensaje) {
        super(mensaje);
    }
    
}

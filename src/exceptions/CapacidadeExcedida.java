package exceptions;

public class CapacidadeExcedida extends RuntimeException {
    public CapacidadeExcedida(String message) {
        super(message);
    }
}

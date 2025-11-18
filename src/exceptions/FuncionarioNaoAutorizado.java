package exceptions;

public class FuncionarioNaoAutorizado extends RuntimeException {
    public FuncionarioNaoAutorizado(String message) {
        super(message);
    }
}

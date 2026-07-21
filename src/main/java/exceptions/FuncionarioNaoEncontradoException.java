package exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(String message) {
        super(message);
    }
}

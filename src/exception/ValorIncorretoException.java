package exception;

public class ValorIncorretoException extends NumberFormatException{
    public ValorIncorretoException (String mensagem) {
        super(mensagem);
    }
}

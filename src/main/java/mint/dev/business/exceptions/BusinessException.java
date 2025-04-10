package mint.dev.business.exceptions;

public class BusinessException extends  RuntimeException{

    private static final long servialVersionUID = 1;
    public BusinessException(String message) {super(message);}
    public BusinessException(Throwable cause) {super(cause);}
    public BusinessException(String message, Throwable couse) {super(message, couse);}

    public BusinessException() {

    }
}

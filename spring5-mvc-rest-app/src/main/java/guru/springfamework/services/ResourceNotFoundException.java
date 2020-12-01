package guru.springfamework.services;

/**
 * Darcy Xian  24/11/20  11:49 am      spring5-mvc-rest
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public ResourceNotFoundException (String message, Throwable cause, boolean enableSuppression, boolean writbleStackTrace){
        super(message,cause,enableSuppression,writbleStackTrace);
    }
}

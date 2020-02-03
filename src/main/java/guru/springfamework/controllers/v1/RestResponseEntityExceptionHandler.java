package guru.springfamework.controllers.v1;

import guru.springfamework.customExceptions.ResourceAlreadyExistsException;
import guru.springfamework.customExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//all controllers will implement these methods
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //this method will handle any ResourceNotFoundExceptions that get thrown
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFoundException(Exception e, WebRequest request){
        //return message and HttpStatus for NOT FOUND
        if(e.getMessage() != null){
            return e.getMessage();
        }

        return "Resource Not Found";
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Object handleAlreadyExistsException(Exception e){
        if(e.getMessage() != null){
            return e.getMessage();
        }
        return "Resource already exists";
    }
}

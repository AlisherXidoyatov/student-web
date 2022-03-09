package Employee.ishchilar.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServerBadRequestException.class})
    public ResponseEntity<?>exceptionHandler(ServerBadRequestException e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }
}

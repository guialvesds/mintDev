package mint.dev.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler  {
//Faz com que exiba apenas as mensagens de exeções de forma global
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleBusinessException(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

}

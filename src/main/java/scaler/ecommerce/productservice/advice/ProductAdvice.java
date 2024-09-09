package scaler.ecommerce.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import scaler.ecommerce.productservice.dto.ErrorDto;
import scaler.ecommerce.productservice.exception.InvalidArgument;
import scaler.ecommerce.productservice.exception.ProductNotFound;

@ControllerAdvice
public class ProductAdvice {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(ProductNotFound e) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidArgument.class)
    public ResponseEntity<ErrorDto> handleInvalidArgument(InvalidArgument e) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The resource you are looking for does not exist.");
    }

}

package scaler.ecommerce.productservice.advice;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
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
        ErrorDto dto = new ErrorDto("",e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidArgument.class)
    public ResponseEntity<ErrorDto> handleInvalidArgument(InvalidArgument e) {
        ErrorDto dto = new ErrorDto("",e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleNoHandlerFoundException(Exception ex) {
        ErrorDto errorResponse = new ErrorDto("Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorDto errorResponse = new ErrorDto("Invalid request body", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorDto> handleMissingPathVariableException(MissingPathVariableException ex) {
        ErrorDto errorResponse = new ErrorDto("Missing path variable", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleTypeMismatchException(TypeMismatchException ex) {
        ErrorDto errorResponse = new ErrorDto("Type mismatch", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}

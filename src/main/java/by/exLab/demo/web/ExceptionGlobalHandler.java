package by.exLab.demo.web;

import by.exLab.demo.core.dto.error.*;
import by.exLab.demo.core.exceptions.*;
import by.exLab.demo.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler
    public ResponseEntity<List<SingleErrorResponse>> handleInputSingleDataException(InputSingleDataException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(new SingleErrorResponse(e.getErrorCode(), e.getMessage())));
    }

    @ExceptionHandler(value = {ConversionTypeException.class, Exception.class})
    public ResponseEntity<List<SingleErrorResponse>> handleConversionTypeException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new SingleErrorResponse(ErrorCode.ERROR, e.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<MultipleErrorResponse> handleMultiErrors(MethodArgumentNotValidException e) {
        List<LocalError> errorList = e.getBindingResult().getAllErrors()
                .stream().map(error -> new  LocalError(((FieldError) error).getField(), error.getDefaultMessage())).toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorResponse(ErrorCode.STRUCTURED_ERROR, errorList));
    }

    @ExceptionHandler
    public ResponseEntity<MultipleErrorResponse> handle(ConstraintViolationException e) {
        List<LocalError> localErrors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            String name = null;
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                name = node.getName();
            }
            localErrors.add(new LocalError(name, constraintViolation.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorResponse(ErrorCode.STRUCTURED_ERROR, localErrors));
    }

    @ExceptionHandler
    public ResponseEntity<List<SingleErrorResponse>> handle(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(new SingleErrorResponse(ErrorCode.ERROR, e.getMessage())));
    }

//500
//    @ExceptionHandler  //(value = {IllegalAccessException.class})
//    public ResponseEntity<List<ErrorForSingleResponse>> handleAll(Throwable e) {
//        List<ErrorForSingleResponse> errors = new ArrayList<>();
//        errors.add(new ErrorForSingleResponse("error",
//                "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"));
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(errors);
//           }

//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<List<ErrorForSingleResponse>> handleNPE(){
//        List<ErrorForSingleResponse> errors = new ArrayList<>();
//        errors.add(new ErrorForSingleResponse("error", "Sorry, but NullPointerException :( "));
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(errors);
//    }

}

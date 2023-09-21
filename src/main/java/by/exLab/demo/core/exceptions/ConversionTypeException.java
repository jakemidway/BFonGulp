package by.exLab.demo.core.exceptions;


import by.exLab.demo.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ConversionTypeException extends RuntimeException {
    private ErrorCode errorCode;

    public ConversionTypeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}


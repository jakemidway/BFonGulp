package by.exLab.demo.core.exceptions;


import by.exLab.demo.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InputSingleDataException extends IllegalArgumentException{
    private ErrorCode errorCode;

    public InputSingleDataException(String s, ErrorCode errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
}

package by.exLab.demo.core.dto.error;


import java.util.List;

public class MultipleErrorResponseDTO {

    private String logref;
    private List<Error> errors;

    public MultipleErrorResponseDTO() {
    }

    public MultipleErrorResponseDTO(String logref, List<Error> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }

    public MultipleErrorResponseDTO(List<Error> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}

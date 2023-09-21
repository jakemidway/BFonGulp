package by.exLab.demo.core.dto.error;

import by.exLab.demo.enums.ErrorCode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MultipleErrorResponse {

    private ErrorCode logref;
    private List<LocalError> errors = new ArrayList<>();

}

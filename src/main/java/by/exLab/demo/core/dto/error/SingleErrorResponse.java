package by.exLab.demo.core.dto.error;

import by.exLab.demo.enums.ErrorCode;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SingleErrorResponse {

    private ErrorCode logref;
    private String message;
}
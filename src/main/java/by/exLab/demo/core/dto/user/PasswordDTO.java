package by.exLab.demo.core.dto.user;

import by.exLab.demo.validator.api.ValidPassword;
import by.exLab.demo.validator.api.ValidString;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    @ValidString
    @ValidPassword
    private String password;
    @ValidString
    private String repeatingPassword;
}

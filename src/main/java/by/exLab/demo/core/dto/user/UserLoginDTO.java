package by.exLab.demo.core.dto.user;

import by.exLab.demo.validator.api.ValidEmail;
import by.exLab.demo.validator.api.ValidPassword;
import by.exLab.demo.validator.api.ValidString;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @ValidEmail
    private String mail;
    @ValidString
    @ValidPassword
    private String password;
}

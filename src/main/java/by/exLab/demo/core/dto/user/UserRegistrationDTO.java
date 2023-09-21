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
public class UserRegistrationDTO {

    @ValidEmail
    private String mail;
    @ValidPassword
    @ValidString
    private String password;
    @ValidString
    private String repeatingPassword;
    @ValidString
    private String firstName;
    @ValidString
    private String lastName;
    private String patronymic;
    private String avatarLink;
    private String phoneNumber;
}

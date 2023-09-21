package by.exLab.demo.core.dto.user;

import by.exLab.demo.enums.UserRole;
import by.exLab.demo.enums.UserStatus;

import by.exLab.demo.validator.api.ValidEmail;
import by.exLab.demo.validator.api.ValidPassword;
import by.exLab.demo.validator.api.ValidString;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    private UUID uuid;
    private LocalDateTime dtUpdate;

    @ValidEmail
    private String mail;
    @ValidString
    @ValidPassword
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
    @NotNull(message = "Введите правильную роль")
    private UserRole userRole;
    @NotNull(message = "Введите правильный статус")
    private UserStatus userStatus;
}

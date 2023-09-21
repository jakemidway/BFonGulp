package by.exLab.demo.service.api;


import by.exLab.demo.core.dto.user.*;
import by.exLab.demo.validator.api.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface IPersonalAccountService {

    boolean save(@NotNull @Valid UserRegistrationDTO userRegistrationDTO);

    UserDTO getCard(UUID id);

    boolean isUserExistByMail(String mail);

    void sendMailAboutRecoveryPassword(@ValidEmail String mail);

    UserDTO login(@NotNull @Valid UserLoginDTO userLoginDTO);

    void mailConfirm(@NotNull UUID uuid, @NotNull  String mail );

    void saveNewPassword(@NotNull UUID uuid,  @Valid PasswordDTO dto);

    void checkPasswordsMatch(String password, String repeatingPassword);
}

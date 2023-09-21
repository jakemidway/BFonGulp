package by.exLab.demo.service.api;

import by.exLab.demo.core.dto.PageDTO;
import by.exLab.demo.core.dto.user.*;

import by.exLab.demo.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAdminService {

    void addNewUser(@NotNull @Valid UserCreateDTO userCreateDTO) ;

    UserDTO getCard(@NotNull UUID uuid);

    void update(@ValidString UUID uuid, @NotNull LocalDateTime dtUpdate,@NotNull @Valid UserCreateDTO userCreateDTO);

    PageDTO<UserDTO> getPage(int numberOfPage, int size);


}

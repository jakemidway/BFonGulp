package by.exLab.demo.service.converters;

import by.exLab.demo.enums.*;
import by.exLab.demo.repositories.entities.RoleEntity;
import by.exLab.demo.repositories.entities.StatusEntity;
import by.exLab.demo.repositories.entities.UserEntity;
import by.exLab.demo.service.converters.api.IConversionToEntity;
import org.springframework.stereotype.Component;

import by.exLab.demo.core.dto.user.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ConversionToEntity implements IConversionToEntity {

    // user registers on  site
    @Override
    public UserEntity convertToEntity(UserRegistrationDTO userRegistrationDTO) {

        return new UserEntity(UUID.randomUUID(),
                LocalDateTime.now(),
                userRegistrationDTO.getMail(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getPatronymic(),
                userRegistrationDTO.getAvatarLink(),
                userRegistrationDTO.getPhoneNumber(),
                new RoleEntity(UserRole.USER),
                new StatusEntity(UserStatus.WAITING_ACTIVATION)
        );
    }

    //admin create user
    @Override
    public UserEntity convertToEntity(UserCreateDTO userCreateDTO) {
       // LocalDateTime dtCreate = LocalDateTime.now();   //.withNano(3);
        return new UserEntity(UUID.randomUUID(),
                LocalDateTime.now(),
                userCreateDTO.getMail(),
                userCreateDTO.getPassword(),
                userCreateDTO.getFirstName(),
                userCreateDTO.getLastName(),
                userCreateDTO.getPatronymic(),
                userCreateDTO.getAvatarLink(),
                userCreateDTO.getPhoneNumber(),
                new RoleEntity(userCreateDTO.getUserRole()),
                new StatusEntity(userCreateDTO.getUserStatus())
               );
    }
}

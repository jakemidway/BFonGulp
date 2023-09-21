package by.exLab.demo.service.converters.api;

import by.exLab.demo.core.dto.user.*;
import by.exLab.demo.repositories.entities.UserEntity;

public interface IConversionToEntity {

    UserEntity convertToEntity(UserRegistrationDTO userRegistrationDTO);
    UserEntity convertToEntity(UserCreateDTO userCreateDTO); //дженерик
}

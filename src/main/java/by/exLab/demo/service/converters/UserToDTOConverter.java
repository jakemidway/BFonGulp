package by.exLab.demo.service.converters;

import by.exLab.demo.core.dto.user.UserDTO;
import by.exLab.demo.repositories.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDTOConverter implements Converter<UserEntity, UserDTO> {
    @Override
    public UserDTO convert(UserEntity userEntity) {
        return new UserDTO( userEntity.getUuid(),
                userEntity.getDtUpdate(), //truncatedTo(ChronoUnit.MICROS),
                userEntity.getMail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPatronymic(),
                userEntity.getAvatarLink(),
                userEntity.getPhoneNumber(),
                userEntity.getRoleEntity().getRole(),
                userEntity.getStatusEntity().getStatus()
        );
    }
}

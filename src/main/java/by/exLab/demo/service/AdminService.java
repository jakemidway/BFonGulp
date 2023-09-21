package by.exLab.demo.service;

import by.exLab.demo.core.dto.PageDTO;
import by.exLab.demo.core.dto.user.UserCreateDTO;
import by.exLab.demo.core.dto.user.UserDTO;
import by.exLab.demo.core.exceptions.InputSingleDataException;
import by.exLab.demo.enums.ErrorCode;
import by.exLab.demo.repositories.IUserRepository;
import by.exLab.demo.repositories.entities.RoleEntity;
import by.exLab.demo.repositories.entities.StatusEntity;
import by.exLab.demo.repositories.entities.UserEntity;
import by.exLab.demo.service.api.IAdminService;
import by.exLab.demo.service.api.IPersonalAccountService;
import by.exLab.demo.service.converters.api.IConversionToEntity;
import by.exLab.demo.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Service
public class AdminService implements IAdminService {
    private final IUserRepository userRepository;
    private final IPersonalAccountService personalAccountService;
    private final ConversionService conversionService;
    private final IConversionToEntity conversionToEntity;

    @Override
    public void addNewUser(@NotNull @Valid UserCreateDTO userCreateDTO) {
        personalAccountService.checkPasswordsMatch(userCreateDTO.getPassword(),userCreateDTO.getRepeatingPassword());
        personalAccountService.isUserExistByMail(userCreateDTO.getMail());

//        if (!conversionService.canConvert(UserCreateDTO.class, UserEntity.class)) {
//            throw new ConversionTypeException("Ошибка конвертации UserCreateDTO", ErrorCode.ERROR);
//        }
        UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
        entity.setPassword(userCreateDTO.getPassword());
        userRepository.save(entity);
    }

    @Override
    public void update(@ValidString UUID uuid, @NotNull LocalDateTime dtUpdate,@NotNull @Valid UserCreateDTO userCreateDTO) {
        Optional<UserEntity> userEntityFromDB = userRepository.findById(uuid);
        if (userEntityFromDB.isEmpty()) {
            throw new InputSingleDataException("Пользователя с id " + uuid + " для обновления не найдено!", ErrorCode.ERROR);
        }
        UserEntity entity = userEntityFromDB.get();
        if (!entity.getDtUpdate().isEqual(dtUpdate) ) {
            throw new InputSingleDataException("Версии для пользователя не совпадают!", ErrorCode.ERROR);
        }
        personalAccountService.checkPasswordsMatch(userCreateDTO.getPassword(),userCreateDTO.getRepeatingPassword());

        String userCreateDTOMail = userCreateDTO.getMail();
        if(!entity.getMail().equals(userCreateDTOMail)) {
            personalAccountService.isUserExistByMail(userCreateDTOMail);
        }
        // String encode = encoder.encode(userCreateDTO.getPassword());
        entity.setMail(userCreateDTO.getMail());
        entity.setPassword(userCreateDTO.getPassword());
        entity.setFirstName(userCreateDTO.getFirstName());
        entity.setLastName(userCreateDTO.getLastName());
        entity.setPatronymic(userCreateDTO.getPatronymic());
        entity.setAvatarLink(userCreateDTO.getAvatarLink());
        entity.setPhoneNumber(userCreateDTO.getPhoneNumber());
        entity.setRoleEntity(new RoleEntity(userCreateDTO.getUserRole()));
        entity.setStatusEntity(new StatusEntity(userCreateDTO.getUserStatus()));
          //  entity.setPassword(encode);
        userRepository.save(entity);
        }

    @Override
    public UserDTO getCard(UUID uuid) {
        return personalAccountService.getCard(uuid);
    }

    @Override
    public PageDTO<UserDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<UserEntity> allEntity = userRepository.findAll(pageable);
        List<UserDTO> content = new ArrayList<>();

        for (UserEntity entity: allEntity) {
            UserDTO userDTO = conversionService.convert(entity, UserDTO.class);
            content.add(userDTO);
        }
        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content);
    }
}

package by.exLab.demo.service;

import by.exLab.demo.core.dto.user.PasswordDTO;
import by.exLab.demo.core.dto.user.UserDTO;
import by.exLab.demo.core.dto.user.UserLoginDTO;
import by.exLab.demo.core.dto.user.UserRegistrationDTO;
import by.exLab.demo.core.exceptions.InputSingleDataException;
import by.exLab.demo.enums.ErrorCode;
import by.exLab.demo.enums.UserStatus;
import by.exLab.demo.repositories.IPersonalAccountRepository;
import by.exLab.demo.repositories.entities.StatusEntity;
import by.exLab.demo.repositories.entities.UserEntity;
import by.exLab.demo.service.api.IPersonalAccountService;
import by.exLab.demo.service.api.ISendingMailService;
import by.exLab.demo.service.converters.api.IConversionToEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Service
public class PersonalAccountService implements IPersonalAccountService {
    private final IPersonalAccountRepository personalAccountRepository;
    private final IConversionToEntity conversionToEntity;
    private final ConversionService conversionService;
    private final ISendingMailService mailService;
    @Value("${spring.data.redis.url}")
    private String url;

    private static final String URL_FOR_CHANGING_PASSWORD = "http://localhost:8080/users/reset_password";
    public static final String SUBJECT_FOR_REGISTRATION = "Регистрация на портале BF-Solutions";
    public static final String SUBJECT_FOR_CHANGING_PASSWORD = "Восстановление пароля для портала BF-Solutions";

    @Override
    public boolean save(@NotNull @Valid UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getRepeatingPassword())){
            throw new InputSingleDataException("Пароли не совпадают", ErrorCode.ERROR);
        }
        isUserExistByMail(userRegistrationDTO.getMail());
//        if (!conversionService.canConvert(UserRegistrationDTO.class, UserEntity.class)) {
//            throw new ConversionTypeException("Ошибка конвертации UserRegistrationDTO", ErrorCode.ERROR);
//        }
        UserEntity entity = conversionToEntity.convertToEntity(userRegistrationDTO);
        //String encode = encoder.encode(entity.getPassword());
        entity.setPassword(userRegistrationDTO.getPassword());
        personalAccountRepository.save(entity);
        String message = String.format("Дорогой пользователь, %s! \n" +
                "Регистрация на сайте BF-Solutions.com прошла успешно. \n" +
                "Для входа в личный кабинет перейдите по ссылке: \n" + "%s/%s/%s" +
                "\n Если вы получили это письмо по ошибке, просто игнорируйте его.",
                entity.getFirstName(),
                url,
                entity.getMail(),
                entity.getUuid()
        );
        mailService.send(entity.getMail(), SUBJECT_FOR_REGISTRATION, message);
        return true;
    }

    @Override
    public UserDTO login(@NotNull @Valid UserLoginDTO userLoginDTO) {
        UserEntity userEntity = personalAccountRepository.findByMail(userLoginDTO.getMail());
        if(userEntity == null){
            throw new InputSingleDataException("Введенный Вами адрес электронной почты не зарегистрирован. " +
                    "Пройдите регистрацию.", ErrorCode.ERROR);
        }
        if(!userEntity.getStatusEntity().getStatus().equals(UserStatus.ACTIVATED)){
            throw new InputSingleDataException("Ваш аккаунт должен быть активирован!", ErrorCode.ERROR);
        }
        return conversionService.convert(userEntity, UserDTO.class );
    }

    @Override
    public void mailConfirm(@NotNull UUID uuid, @NotNull  String mail) {
        if (!personalAccountRepository.existsByMail(mail)) {
            throw new InputSingleDataException("Извините, произошла ошибка при обработке вашего запроса на аутентификацию.", ErrorCode.ERROR);
        }
        UserEntity userEntity = personalAccountRepository.findByMail(mail);
        userEntity.setStatusEntity(new StatusEntity(UserStatus.ACTIVATED));
        personalAccountRepository.save(userEntity);
        System.out.println("Активация пройдена");
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        UserEntity userEntity = personalAccountRepository.findById(uuid)
                .orElseThrow(() -> new InputSingleDataException("Пользователя с id " + uuid + " нет в базе данных!", ErrorCode.ERROR));
        return conversionService.convert(userEntity, UserDTO.class);
    }

    @Override
    public void checkPasswordsMatch(String password, String repeatingPassword){
        if(!password.equals(repeatingPassword)){
            throw new InputSingleDataException("Пароли не совпадают", ErrorCode.ERROR);
        }
    }

//    @Override
//    public UserDTO aboutMe(String mail) {
//        UserEntity entityByMail = personalAccountRepository.findByMail(mail);
//        return conversionService.convert(entityByMail, UserDTO.class);
//    }

    @Override
    public void sendMailAboutRecoveryPassword( String mail) {
        if(!personalAccountRepository.existsByMail(mail)){
            throw new InputSingleDataException("Введенный Вами адрес электронной почты не зарегистрирован.",ErrorCode.ERROR);
        }
        UserEntity entity = personalAccountRepository.findByMail(mail);
        String message = String.format("Дорогой пользователь, %s! \n" +
                        "Для восстановления пароля для портала пройдите по ссылке: \n" + "%s/%s/%s" +
                        "\n Если вы получили это письмо по ошибке, просто игнорируйте его.",
                entity.getFirstName(),
                URL_FOR_CHANGING_PASSWORD,
                entity.getMail(),
                entity.getUuid()
        );
        mailService.send(entity.getMail(), SUBJECT_FOR_CHANGING_PASSWORD, message);
    }

    @Override
    public boolean isUserExistByMail(String mail) {
        if (personalAccountRepository.existsByMail(mail)){
            throw new InputSingleDataException("Введенный Вами адрес электронной почты: "
                    + mail + " уже зарегистрирован. ", ErrorCode.ERROR);
        }
        return false;
    }

    @Override
    public void saveNewPassword(@NotNull UUID uuid,  @Valid PasswordDTO dto) {
        if (!dto.getPassword().equals(dto.getRepeatingPassword())){
            throw new InputSingleDataException("Пароли не совпадают", ErrorCode.ERROR);
        }
        UserEntity userEntity = personalAccountRepository.findById(uuid).get();
        userEntity.setPassword(dto.getPassword());
        personalAccountRepository.save(userEntity);
    }
}
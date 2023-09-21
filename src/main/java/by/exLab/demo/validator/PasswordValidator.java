package by.exLab.demo.validator;

import by.exLab.demo.validator.api.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_REGEX ="((?=.*\\d)(?=.*[a-zA-Z]).{8,14})";
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern pattern = Pattern.compile(PASSWORD_REGEX );
        return value != null && pattern.matcher(value).matches();

    //    TO DO: пропускает спецсимволы в пароле
    }
}

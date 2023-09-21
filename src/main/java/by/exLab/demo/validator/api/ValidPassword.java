package by.exLab.demo.validator.api;

import by.exLab.demo.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Вами введен некорректный пароль. Пароль должен быть от 8 до 14 символов, " +
            "может содержать строчные или прописные буквы латинского алфавита и арабские цифры.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
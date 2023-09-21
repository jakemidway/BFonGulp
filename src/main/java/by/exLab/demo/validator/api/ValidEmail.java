package by.exLab.demo.validator.api;


import by.exLab.demo.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= EmailValidator.class)
public @interface ValidEmail {
    String message() default "Вами введен некорректный адрес электронной почты. Пожалуйста, проверьте адрес.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

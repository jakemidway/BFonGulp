package by.exLab.demo.validator.api;

import by.exLab.demo.validator.StringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= StringValidator.class)
public @interface ValidString {
    String message() default "Пожалуйста, заполните поля";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

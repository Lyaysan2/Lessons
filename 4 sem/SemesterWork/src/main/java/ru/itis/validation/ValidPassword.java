package ru.itis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "invalid password";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};
}
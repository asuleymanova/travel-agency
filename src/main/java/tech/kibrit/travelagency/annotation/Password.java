package tech.kibrit.travelagency.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RUNTIME)
public @interface Password {
    String message() default "Password is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

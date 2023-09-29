package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.util.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CategoryEnumValidator.class)
public @interface CategoryEnum {
    String message() default "Invalid category value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

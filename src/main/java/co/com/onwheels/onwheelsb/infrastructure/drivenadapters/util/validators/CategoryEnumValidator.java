package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.util.validators;

import co.com.onwheels.onwheelsb.domain.model.product.enums.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CategoryEnumValidator implements ConstraintValidator<CategoryEnum, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(Category.values())
                .map(Category::name)
                .anyMatch(name -> name.equalsIgnoreCase(value));
    }
}

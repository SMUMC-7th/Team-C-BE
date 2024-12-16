package umc.teamc.youthStepUp.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.teamc.youthStepUp.validation.validator.DayCheckValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DayCheckValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDay {
    String message() default "사용할 수 없는 Day 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

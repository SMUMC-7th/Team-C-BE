package umc.teamc.youthStepUp.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.teamc.youthStepUp.validation.validator.MonthCheckValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MonthCheckValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMonth {
    String message() default "사용할 수 없는 Month 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

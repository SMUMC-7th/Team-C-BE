package umc.teamc.youthStepUp.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.teamc.youthStepUp.validation.ValidateErrorCode;
import umc.teamc.youthStepUp.validation.annotation.CheckDay;

@Component
@RequiredArgsConstructor
public class DayCheckValidator implements ConstraintValidator<CheckDay, Integer> {
    @Override
    public boolean isValid(Integer day, ConstraintValidatorContext context) {
        if (day < 1 || day > 31) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidateErrorCode.UNVALID_MONTH.getMessage())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }


}

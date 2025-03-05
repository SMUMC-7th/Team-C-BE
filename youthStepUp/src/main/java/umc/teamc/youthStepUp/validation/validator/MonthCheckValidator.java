package umc.teamc.youthStepUp.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.teamc.youthStepUp.validation.ValidateErrorCode;
import umc.teamc.youthStepUp.validation.annotation.CheckMonth;

@Component
@RequiredArgsConstructor
public class MonthCheckValidator implements ConstraintValidator<CheckMonth, Integer> {
    @Override
    public boolean isValid(Integer month, ConstraintValidatorContext context) {
        if (month < 1 || month > 12) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidateErrorCode.UNVALID_MONTH.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}

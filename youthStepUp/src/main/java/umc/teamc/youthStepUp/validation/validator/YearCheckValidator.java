package umc.teamc.youthStepUp.validation.validator;

import ch.qos.logback.core.status.ErrorStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.teamc.youthStepUp.validation.ValidateErrorCode;
import umc.teamc.youthStepUp.validation.annotation.CheckYear;

@Component
@RequiredArgsConstructor
public class YearCheckValidator implements ConstraintValidator<CheckYear, Integer> {
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null || year <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidateErrorCode.UNVALID_YEAR.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}



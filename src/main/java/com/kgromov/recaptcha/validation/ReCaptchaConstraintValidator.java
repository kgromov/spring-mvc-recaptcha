package com.kgromov.recaptcha.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReCaptchaConstraintValidator implements ConstraintValidator<ValidReCaptcha, String> {
    private final ReCaptchaService reCaptchaService;

    @Override
    public boolean isValid(String reCaptchaResponse, ConstraintValidatorContext context) {
        return reCaptchaService.validate(reCaptchaResponse);
    }
}

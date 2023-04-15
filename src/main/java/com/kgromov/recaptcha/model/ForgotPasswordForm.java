package com.kgromov.recaptcha.model;


import com.kgromov.recaptcha.validation.ValidReCaptcha;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ForgotPasswordForm {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @ValidReCaptcha
    private String reCaptchaResponse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReCaptchaResponse() {
        return reCaptchaResponse;
    }

    public void setReCaptchaResponse(String reCaptchaResponse) {
        this.reCaptchaResponse = reCaptchaResponse;
    }
}

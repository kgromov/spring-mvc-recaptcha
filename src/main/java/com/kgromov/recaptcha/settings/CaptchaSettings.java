package com.kgromov.recaptcha.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "google.recaptcha")
@Getter
@Setter
public class CaptchaSettings {
    private String url;
    private String key;
    private String secret;
}

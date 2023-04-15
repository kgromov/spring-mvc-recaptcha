package com.kgromov.recaptcha.validation;

import com.kgromov.recaptcha.settings.CaptchaSettings;
import com.kgromov.recaptcha.model.ReCaptchaResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReCaptchaService {
    private final CaptchaSettings captchaSettings;
    private final HttpServletRequest request;

    public boolean validate(String reCaptchaResponse){
        URI verifyUri = UriComponentsBuilder
                .fromHttpUrl(captchaSettings.getUrl())
                .queryParam("secret", captchaSettings.getSecret())
                .queryParam("response", reCaptchaResponse)
                .queryParam("remoteip", request.getRemoteAddr())
                .build()
                .toUri();
     /*   URI verifyUri = URI.create(String.format(
                captchaSettings.getUrl() + "?secret=%s&response=%s&remoteip=%s",
                captchaSettings.getSecret(),
                reCaptchaResponse,
                request.getRemoteAddr()
        ));*/

        try {
            ReCaptchaResponse response = new RestTemplate().getForObject(verifyUri, ReCaptchaResponse.class);
            return response.isSuccess();
        } catch (Exception e){
            log.error("Can't process recaptcha response: ", e);
            return false;
        }
    }

}

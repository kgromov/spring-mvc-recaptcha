package com.kgromov.recaptcha.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
/*@JsonPropertyOrder({
        "success",
        "challenge_ts",
        "hostname",
        "error-codes"
})*/
public class ReCaptchaResponse {
    private boolean success;

    @JsonProperty("challenge_ts")
    private Date challengeTs;

    private String hostname;

    @JsonProperty("error-codes")
    private ErrorCode[] errorCodes;

    @JsonIgnore
    public boolean hasClientError() {
        ErrorCode[] errors = getErrorCodes();
        if(errors == null) {
            return false;
        }
        for(ErrorCode error : errors) {
            switch(error) {
                case INVALID_RESPONSE:
                case MISSING_RESPONSE:
                    return true;
            }
        }
        return false;
    }

    enum ErrorCode {
        MISSING_SECRET,
        INVALID_SECRET,
        MISSING_RESPONSE,
        INVALID_RESPONSE;

        private static final Map<String, ErrorCode> errorsMap = Map.of(
                "missing-input-secret", MISSING_SECRET,
                "invalid-input-secret", INVALID_SECRET,
                "missing-input-response", MISSING_RESPONSE,
                "invalid-input-response", INVALID_RESPONSE
        );

        @JsonCreator
        public static ErrorCode forValue(String value) {
            return errorsMap.get(value.toLowerCase());
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getChallengeTs() {
        return challengeTs;
    }

    public void setChallengeTs(Date challengeTs) {
        this.challengeTs = challengeTs;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public ErrorCode[] getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ErrorCode[] errorCodes) {
        this.errorCodes = errorCodes;
    }
}

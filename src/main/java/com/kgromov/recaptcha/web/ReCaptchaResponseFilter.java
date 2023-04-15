package com.kgromov.recaptcha.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReCaptchaResponseFilter extends OncePerRequestFilter {

    private static final String RE_CAPTCHA_ALIAS = "reCaptchaResponse";
    private static final String RE_CAPTCHA_RESPONSE = "g-recaptcha-response";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getParameter(RE_CAPTCHA_RESPONSE) != null) {
            ReCaptchaHttpServletRequest reCaptchaRequest = new ReCaptchaHttpServletRequest(request);
            filterChain.doFilter(reCaptchaRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
    private static class ReCaptchaHttpServletRequest extends HttpServletRequestWrapper {

        final Map<String, String[]> params;

        ReCaptchaHttpServletRequest(HttpServletRequest request) {
            super(request);
            params = new HashMap<>(request.getParameterMap());
            params.put(RE_CAPTCHA_ALIAS, request.getParameterValues(RE_CAPTCHA_RESPONSE));
        }

        @Override
        public String getParameter(String name) {
            return params.containsKey(name) ? params.get(name)[0] : null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return params;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(params.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }
    }
}

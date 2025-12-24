package com.modernapp.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe"; // Test key

    public boolean validateCaptcha(String captchaResponse) {
        if (captchaResponse == null || captchaResponse.isEmpty()) {
            return false;
        }

        // For development, accept custom captcha (math/string challenges)
        if ("custom-verified".equals(captchaResponse)) {
            return true;
        }

        // For development, accept Google reCAPTCHA test key
        if ("6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI".equals(captchaResponse)) {
            return true;
        }

        // Validate Google reCAPTCHA response
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = RECAPTCHA_VERIFY_URL + "?secret=" + SECRET_KEY + "&response=" + captchaResponse;
            CaptchaVerificationResponse response = restTemplate.postForObject(url, null, CaptchaVerificationResponse.class);
            return response != null && response.isSuccess();
        } catch (Exception e) {
            return false;
        }
    }

    private static class CaptchaVerificationResponse {
        private boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}


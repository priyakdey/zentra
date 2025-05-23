package com.priyakdey.com.zentra.controller;

import com.priyakdey.com.zentra.exception.InvalidRequestException;
import com.priyakdey.com.zentra.model.dto.AuthDto;
import com.priyakdey.com.zentra.model.request.NewAccountRequest;
import com.priyakdey.com.zentra.model.response.AuthResponse;
import com.priyakdey.com.zentra.security.core.SecureCharSequence;
import com.priyakdey.com.zentra.service.AuthenticationService;
import com.priyakdey.com.zentra.util.validator.NewAccountRequestValidator;
import com.priyakdey.com.zentra.util.validator.core.ValidationResult;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/v1/signup", produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
public class SignupController {
    private static final Logger log = LoggerFactory.getLogger(SignupController.class);

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public SignupController(AuthenticationService authenticationService,
                            PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> signup(@RequestBody NewAccountRequest newAccountRequest,
                                               HttpServletResponse response) {
        log.info("Signup request received");
        ValidationResult result = NewAccountRequestValidator.isValidName()
                .and(NewAccountRequestValidator.isValidEmail())
                .and(NewAccountRequestValidator.isValidPassword())
                .apply(newAccountRequest);

        if (!result.isSuccess()) {
            throw new InvalidRequestException(result.message());
        }

        String email = newAccountRequest.getEmail();
        SecureCharSequence rawPassword = newAccountRequest.getPassword();
        String password = passwordEncoder.encode(rawPassword);
        rawPassword.clear();

        String name = newAccountRequest.getName();

        AuthDto authDto = authenticationService.createAccount(name, email, password);
        AuthResponse authResponse = new AuthResponse();
        Integer accountId = authDto.accountId();
        authResponse.setAccountId(accountId);

        String token = authDto.token();
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(3600)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        URI location = URI.create("/v1/account/" + accountId);
        return ResponseEntity.created(location).body(authResponse);
    }

}

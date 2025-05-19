package com.priyakdey.com.zentra.controller;

import com.priyakdey.com.zentra.model.dto.AuthDto;
import com.priyakdey.com.zentra.model.request.LoginRequest;
import com.priyakdey.com.zentra.model.response.AuthResponse;
import com.priyakdey.com.zentra.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/v1/login", produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest,
                                              HttpServletResponse response) {
        AuthDto authDto = authenticationService.authenticate(loginRequest.getEmail(),
                loginRequest.getPassword());

        ResponseCookie cookie = ResponseCookie.from("token", authDto.token())
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(3600)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccountId(authDto.accountId());
        return ResponseEntity.ok(authResponse);
    }

}

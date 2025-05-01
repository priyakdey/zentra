package com.priyakdey.zentra.controller;

import com.priyakdey.zentra.model.request.UserLoginRequest;
import com.priyakdey.zentra.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/api/login", produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
public class LoginController {

    private final LoginService loginService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(LoginService loginService, BCryptPasswordEncoder passwordEncoder) {
        this.loginService = loginService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        char[] _password = loginRequest.getPassword();
        Arrays.fill(_password, '\0');
        String password = passwordEncoder.encode(new String(_password));

        String token = loginService.generateToken(username, password);
        return ResponseEntity.ok(token);
    }

}

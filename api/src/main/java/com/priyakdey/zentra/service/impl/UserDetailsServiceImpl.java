package com.priyakdey.zentra.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.priyakdey.zentra.entity.Account;
import com.priyakdey.zentra.exception.AccountNotActivatedException;
import com.priyakdey.zentra.exception.InvalidCredentialsException;
import com.priyakdey.zentra.exception.UserNotFoundException;
import com.priyakdey.zentra.repository.AccountRepository;
import com.priyakdey.zentra.service.LoginService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.EMPTY_LIST;

/**
 * @author Priyak Dey
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, LoginService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(AccountRepository accountRepository,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No such user found"));
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(),
                true, true, true,
                EMPTY_LIST);
    }

    @Override
    public String generateToken(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (!(Objects.equals(username, userDetails.getUsername())
                && !passwordEncoder.matches(password, userDetails.getPassword()))) {
            throw new InvalidCredentialsException("Wrong username or password");
        }

        if (!userDetails.isEnabled()) {
            throw new AccountNotActivatedException("Account is not activated");
        }

        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        Instant iat = now.atZone(ZoneId.systemDefault()).toInstant();
        Instant eat = iat.plus(7, ChronoUnit.DAYS);

        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("typ", "JWT");

        // TODO: Fetch issuer from config/env
        // TODO: Fetch secret from config/env
        // TODO: Refactor to have support for multiple algorithms
        return JWT.create()
                .withHeader(headerClaims)
                .withIssuer("zentra")
                .withSubject(username)
                .withIssuedAt(iat)
                .withExpiresAt(eat)
                .sign(Algorithm.HMAC256("secret"));
    }
}

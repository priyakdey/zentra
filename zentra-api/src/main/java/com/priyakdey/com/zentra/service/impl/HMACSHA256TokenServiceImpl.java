package com.priyakdey.com.zentra.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.priyakdey.com.zentra.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author Priyak Dey
 */
@Service
public class HMACSHA256TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(Integer accountId) {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        Map<String, Object> header = Map.of("alg", algorithm.getName(), "typ", "JWT");

        Instant iat = Instant.now(Clock.systemUTC());
        Instant eat = iat.plus(1, ChronoUnit.HOURS);

        return JWT.create()
                .withHeader(header)
                .withSubject(accountId.toString())
                .withIssuer("zentra")
                .withIssuedAt(iat)
                .withExpiresAt(eat)
                .sign(algorithm);

    }
}

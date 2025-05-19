package com.priyakdey.com.zentra.service;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Priyak Dey
 */
public interface TokenService {

    String generateToken(Integer accountId);

    DecodedJWT verifyToken(String token);
}

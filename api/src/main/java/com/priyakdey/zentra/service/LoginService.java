package com.priyakdey.zentra.service;

/**
 * @author Priyak Dey
 */
public interface LoginService {

    String generateToken(String username, String password);

}

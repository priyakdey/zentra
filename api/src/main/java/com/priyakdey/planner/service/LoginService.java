package com.priyakdey.planner.service;

/**
 * @author Priyak Dey
 */
public interface LoginService {

    String generateToken(String username, String password);

}

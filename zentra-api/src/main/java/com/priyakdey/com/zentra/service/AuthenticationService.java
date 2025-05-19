package com.priyakdey.com.zentra.service;

import com.priyakdey.com.zentra.model.dto.AuthDto;

/**
 * @author Priyak Dey
 */
public interface AuthenticationService {

    AuthDto createAccount(String email, String password);

}

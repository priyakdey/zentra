package com.priyakdey.com.zentra.service;

import com.priyakdey.com.zentra.model.dto.AuthDto;
import com.priyakdey.com.zentra.security.SecureCharSequence;

/**
 * @author Priyak Dey
 */
public interface AuthenticationService {

    AuthDto createAccount(String email, String password);

    AuthDto authenticate(String email, SecureCharSequence password);
}

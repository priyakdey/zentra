package com.priyakdey.com.zentra.service.impl;

import com.priyakdey.com.zentra.domain.Account;
import com.priyakdey.com.zentra.exception.EmailExistsException;
import com.priyakdey.com.zentra.exception.InvalidCredentialsException;
import com.priyakdey.com.zentra.model.dto.AuthDto;
import com.priyakdey.com.zentra.repository.AccountRepository;
import com.priyakdey.com.zentra.security.core.SecureCharSequence;
import com.priyakdey.com.zentra.service.AuthenticationService;
import com.priyakdey.com.zentra.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Priyak Dey
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AccountRepository accountRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AccountRepository accountRepository,
                                     TokenService tokenService,
                                     PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AuthDto createAccount(String name, String email, String password) {
        log.info("Trying to create a new account");

        if (accountRepository.existsByEmail(email)) {
            throw new EmailExistsException();
        }

        Account account = new Account(name, email, password);
        account = accountRepository.save(account);

        Integer accountId = account.getId();

        String token = tokenService.generateToken(accountId);
        return new AuthDto(accountId, token);
    }

    @Override
    public AuthDto authenticate(String email, SecureCharSequence password) {
        Optional<Account> optional = accountRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        Account account = optional.get();
        if (!passwordEncoder.matches(password, account.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = tokenService.generateToken(account.getId());
        return new AuthDto(account.getId(), token);
    }
}

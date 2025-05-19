package com.priyakdey.com.zentra.service.impl;

import com.priyakdey.com.zentra.domain.Account;
import com.priyakdey.com.zentra.exception.EmailExistsException;
import com.priyakdey.com.zentra.model.dto.AuthDto;
import com.priyakdey.com.zentra.repository.AccountRepository;
import com.priyakdey.com.zentra.service.AuthenticationService;
import com.priyakdey.com.zentra.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Priyak Dey
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AccountRepository accountRepository;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(AccountRepository accountRepository,
                                     TokenService tokenService) {
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AuthDto createAccount(String email, String password) {
        log.info("Trying to create a new account");

        if (accountRepository.existsByEmail(email)) {
            throw new EmailExistsException();
        }

        Account account = new Account(email, password);
        account = accountRepository.save(account);

        Integer accountId = account.getId();

        String token = tokenService.generateToken(accountId);
        return new AuthDto(accountId, token);
    }
}

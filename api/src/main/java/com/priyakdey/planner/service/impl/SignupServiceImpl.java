package com.priyakdey.planner.service.impl;

import com.priyakdey.planner.entity.Account;
import com.priyakdey.planner.exception.EmailExistsException;
import com.priyakdey.planner.exception.UsernameExistsException;
import com.priyakdey.planner.repository.AccountRepository;
import com.priyakdey.planner.service.SignupService;
import org.springframework.stereotype.Service;

/**
 * @author Priyak Dey
 */
@Service
public class SignupServiceImpl implements SignupService {

    private final AccountRepository accountRepository;

    public SignupServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public int signup(String name, String username, String password, String email) {
        if (accountRepository.existsByUsername(username)) {
            throw new UsernameExistsException("Username is taken");
        }

        if (accountRepository.existsByEmail(email)) {
            throw new EmailExistsException("This email is already registered");
        }

        Account account = new Account(username, email, password, name);
        account = accountRepository.save(account);
        return account.getId();
    }
}

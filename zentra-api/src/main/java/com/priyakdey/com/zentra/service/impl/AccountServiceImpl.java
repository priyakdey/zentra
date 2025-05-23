package com.priyakdey.com.zentra.service.impl;

import com.priyakdey.com.zentra.domain.Account;
import com.priyakdey.com.zentra.exception.AccountNotFoundException;
import com.priyakdey.com.zentra.model.dto.AccountDto;
import com.priyakdey.com.zentra.repository.AccountRepository;
import com.priyakdey.com.zentra.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Priyak Dey
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto getAccountDetails(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        Account account = optional.get();
        return new AccountDto(id, account.getName());
    }
}

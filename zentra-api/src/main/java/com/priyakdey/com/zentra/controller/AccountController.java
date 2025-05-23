package com.priyakdey.com.zentra.controller;

import com.priyakdey.com.zentra.model.dto.AccountDto;
import com.priyakdey.com.zentra.model.response.AccountDetailsResponse;
import com.priyakdey.com.zentra.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/v1/me", produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<AccountDetailsResponse> getProfileDetails(Principal principal) {
        int id = Integer.parseInt(principal.getName());
        AccountDto accountDto = accountService.getAccountDetails(id);
        AccountDetailsResponse accountDetailsResponse = new AccountDetailsResponse();
        accountDetailsResponse.setId(id);
        accountDetailsResponse.setName(accountDto.name());
        return ResponseEntity.ok(accountDetailsResponse);
    }

}

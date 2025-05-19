package com.priyakdey.com.zentra.model.response;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class AuthResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -3672169172900559590L;

    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}

package com.priyakdey.com.zentra.model.response;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class AccountDetailsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1866196554206380627L;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

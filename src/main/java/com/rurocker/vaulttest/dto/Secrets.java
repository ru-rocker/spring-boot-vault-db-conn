package com.rurocker.vaulttest.dto;

import java.io.Serializable;

public class Secrets implements Serializable {

    private static final long serialVersionUID = -4215100319902481003L;
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}

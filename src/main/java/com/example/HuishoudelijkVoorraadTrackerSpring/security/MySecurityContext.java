package com.example.HuishoudelijkVoorraadTrackerSpring.security;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private static final Logger LOG = LogManager.getLogger(MySecurityContext.class);

    private Account account;
    private String scheme;

    public MySecurityContext(Account user, String scheme) {
        this.account = user;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return (Principal) this.account;
    }

    @Override
    public boolean isUserInRole(String s) {
        if (account.getRole() != null) {
            LOG.info("given role {} equals user's role {}}", s, account.getRole());
            return s.equals(account.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
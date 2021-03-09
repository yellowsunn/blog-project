package com.yellowsunn.springblog.security;

import com.yellowsunn.springblog.domain.entity.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode(of = "account", callSuper = false)
public class CustomUserDetails extends User {

    private final Account account;

    public CustomUserDetails(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
    }
}

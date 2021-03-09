package com.yellowsunn.springblog.security.service;

import com.yellowsunn.springblog.domain.entity.Account;
import com.yellowsunn.springblog.repository.AccountRepository;
import com.yellowsunn.springblog.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        Account account = accountOptional.get();

        return new CustomUserDetails(account, List.of(new SimpleGrantedAuthority(account.getRole())));
    }
}

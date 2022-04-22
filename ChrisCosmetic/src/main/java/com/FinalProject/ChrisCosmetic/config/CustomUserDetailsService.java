package com.FinalProject.ChrisCosmetic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Account account = accountRepository.findAccountByEmail(email);
	if (account == null) {
	    throw new UsernameNotFoundException("Account not found");
	}
	return new CustomUserDetails(account);
    }

}

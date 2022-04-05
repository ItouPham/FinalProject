package com.FinalProject.ChrisCosmetic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(Account user) {
	accountRepository.save(user);
    }

    @Override
    public List<Account> findAll() {
	return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
	return accountRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
	Account account = accountRepository.findById(id).get();
	accountRepository.delete(account);
    }

}

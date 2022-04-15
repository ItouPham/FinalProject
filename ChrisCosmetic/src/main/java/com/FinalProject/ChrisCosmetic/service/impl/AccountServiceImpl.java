package com.FinalProject.ChrisCosmetic.service.impl;

import java.util.List;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void save(AccountDTO accountDTO) {
        accountRepository.save(accountMapper.toEntity(accountDTO));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id).get();
        accountRepository.delete(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}

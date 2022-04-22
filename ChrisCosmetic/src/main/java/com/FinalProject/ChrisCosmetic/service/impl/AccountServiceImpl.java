package com.FinalProject.ChrisCosmetic.service.impl;

import java.util.List;
import java.util.Optional;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(AccountDTO accountDTO) {
        accountRepository.save(accountMapper.toEntity(accountDTO));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountDTO findById(String id) {
        return accountRepository.findById(id).map(accountMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public void delete(String id) {
        Account account = accountRepository.findById(id).get();
        accountRepository.delete(account);
    }

    @Override
    public Account login(String email, String password) {
        Optional<Account> optExist = accountRepository.findByEmail(email);

        if(optExist.isPresent() && bCryptPasswordEncoder.matches(password,optExist.get().getPassword())){
            optExist.get().setPassword("");
            return optExist.get();
        }
        return null;
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

}

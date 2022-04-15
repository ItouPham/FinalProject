package com.FinalProject.ChrisCosmetic.service;

import java.util.List;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.entity.Account;

public interface AccountService {
    List<Account> findAll();

    Account findByEmail(String email);

    AccountDTO findById(Long id);

    void save(AccountDTO accountDTO);

    void delete(Long id);
}

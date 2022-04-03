package com.FinalProject.ChrisCosmetic.service;

import java.util.List;

import com.FinalProject.ChrisCosmetic.entity.Account;

public interface AccountService {
    List<Account> findAll();

//    Optional<Account> findAccountByEmail(String email);
//
//    boolean accountExisted(String email);

    Account findById(Long id);

    void save(Account user);

    void delete(Long id);
}

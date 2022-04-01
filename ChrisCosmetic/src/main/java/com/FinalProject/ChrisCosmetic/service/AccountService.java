package com.FinalProject.ChrisCosmetic.service;

import java.util.List;

import javax.validation.Valid;

import com.FinalProject.ChrisCosmetic.entity.Account;

public interface AccountService {
	List<Account> findAll();
	Account findById(Long id);
	void save(Account user);
	void delete(Long id);
}

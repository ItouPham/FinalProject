package com.FinalProject.ChrisCosmetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FinalProject.ChrisCosmetic.entity.Account;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    Account findByEmail(String email);

//    Optional<Account> findByEmail(String email);
}

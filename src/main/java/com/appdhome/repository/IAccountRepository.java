package com.appdhome.repository;

import com.appdhome.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    public Optional<Account> findByUsername(String username);
    public Optional<Account> findByUsernameAndPassword(String username, String password);
}

package com.appdhome.services;

import com.appdhome.entities.Account;
import com.appdhome.entities.Appointment;

import java.util.Optional;

public interface IAccountService extends CrudService<Account>{
    public Optional<Account> findByUsername(String username) throws Exception;
    public Optional<Account> findByUsernameAndPassword(String username, String password) throws Exception;
}

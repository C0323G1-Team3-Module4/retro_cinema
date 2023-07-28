package com.example.retro_cinema.user.service.account;


import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;

import java.util.List;

public interface IAccountService {
    AccountUser findByEmail(String name);
    Roles findRoleById(int id);
    void createAccount(AccountUser accountUser);
    List<AccountUser> findAll();
}

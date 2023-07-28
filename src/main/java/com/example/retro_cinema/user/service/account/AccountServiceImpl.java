package com.example.retro_cinema.user.service.account;


import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;
import com.example.retro_cinema.user.repository.accounts.IAccountRepository;
import com.example.retro_cinema.user.repository.roles.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService{
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public AccountUser findByEmail(String email) {
        if (iAccountRepository.findAccountUserByEmail(email) == null){
            return null;
        }
        return iAccountRepository.findAccountUserByEmail(email);
    }

    @Override
    public Roles findRoleById(int id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public void createAccount(AccountUser accountUser) {
        iAccountRepository.save(accountUser);
    }

    @Override
    public List<AccountUser> findAll() {
        return iAccountRepository.findAll();
    }
}

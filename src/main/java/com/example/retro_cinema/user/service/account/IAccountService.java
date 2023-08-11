package com.example.retro_cinema.user.service.account;


import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAccountService {
    AccountUser findByEmail(String name);
    Roles findRoleById(int id);
    AccountUser findByIdAccount(Integer id);
    void createAccount(AccountUser accountUser);
    List<AccountUser> findAll();
    void reset(AccountUser accountUser);
    void sendVerificationReset(AccountUser accountUser, String siteURL) throws UnsupportedEncodingException, MessagingException;
    void sendVerificationEmail(AccountUser accountUser, String siteURL) throws UnsupportedEncodingException, MessagingException;
    boolean verify(String verificationCode);
    AccountUser findByCode(String code);
    boolean verifyReset(String verificationCode);
    AccountUser findByUsername(String username);
    void reset_pw(AccountUser accountUser, String new_pw);
}

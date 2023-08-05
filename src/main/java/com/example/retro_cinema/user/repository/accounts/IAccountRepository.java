package com.example.retro_cinema.user.repository.accounts;


import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.user.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountRepository extends JpaRepository<AccountUser, Integer> {
    AccountUser findAccountUserByEmail(String email);
    List<AccountUser> findByEmail(String email);
    AccountUser findAccountByUsername(String username);
    AccountUser findByVerificationCode(String code);
}

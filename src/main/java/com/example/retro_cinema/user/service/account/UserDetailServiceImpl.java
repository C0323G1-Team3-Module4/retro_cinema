package com.example.retro_cinema.user.service.account;

import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.repository.accounts.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountUser accountUser = this.iAccountRepository.findAccountUserByEmail(email);
        if (accountUser == null) {
            System.out.println("User name not found" + email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        System.out.println("Found User: " + email);
        List<AccountUser> accountUserList = this.iAccountRepository.findByEmail(email);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (accountUserList != null) {
            for (AccountUser user : accountUserList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles().getRoleName());
                authorityList.add(authority);
            }
        }
        return new User(accountUser.getEmail(), accountUser.getPass(), authorityList);
    }
}

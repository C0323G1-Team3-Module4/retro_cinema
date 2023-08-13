package com.example.retro_cinema.user.service.account;

import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;
import com.example.retro_cinema.user.repository.accounts.IAccountRepository;
import com.example.retro_cinema.user.repository.roles.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountUser accountUser = this.accountRepository.findAccountByUsername(userName);

        if (accountUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + accountUser.getUsername());

        // [Find Role]

        String role = accountUser.getRoles().getRoleName();

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        System.out.println("Authority: "  + authority);
        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(accountUser.getUsername(), accountUser.getPass(), accountUser.isEnabled(), true, true, true, grantList);

        return userDetails;
    }
}

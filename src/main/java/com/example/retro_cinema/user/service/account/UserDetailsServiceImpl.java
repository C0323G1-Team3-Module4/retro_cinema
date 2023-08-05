package com.example.retro_cinema.user.service.account;

import com.example.retro_cinema.user.model.AccountUser;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountUser appUser = this.accountRepository.findAccountByUsername(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser.getUsername());

        // [Find Role]

        String role = appUser.getRoles().getRoleName();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), //
                appUser.getPass(), grantList);

        return userDetails;
    }

}

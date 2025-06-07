package com.example.demo.Services;

import com.example.demo.Repository.UserDetailsRepo;
import com.example.demo.module.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsersInfo> usersInfo = userDetailsRepo.getByUserName(username);

        if(usersInfo.isPresent())
        {
            return  new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return List.of(new SimpleGrantedAuthority("ROLE_"+usersInfo.get().getUserRole()));
                }

                @Override
                public String getPassword() {
                    return usersInfo.get().getPassWord();
                }

                @Override
                public String getUsername() {
                    return usersInfo.get().getUserName();
                }
            };
        }
        else
        {
            throw  new UsernameNotFoundException("No User Name is found");
        }

    }
}

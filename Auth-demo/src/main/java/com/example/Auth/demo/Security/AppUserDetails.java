package com.example.Auth.demo.Security;

import com.example.Auth.demo.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private final User user;

    public AppUserDetails(User user){
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }
}

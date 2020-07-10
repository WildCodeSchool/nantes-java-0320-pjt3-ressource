package com.wildcodeschool.ressource.config;

import com.wildcodeschool.ressource.entity.Admin;
import com.wildcodeschool.ressource.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (!optionalAdmin.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        Admin admin = optionalAdmin.get();

        // set role from Hibernate
        // https://stackoverflow.com/a/11746720
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(admin.getRole().getRole()));

        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
                grantedAuthorities);
    }
}

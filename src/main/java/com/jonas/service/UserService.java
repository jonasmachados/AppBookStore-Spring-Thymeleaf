package com.jonas.service;

import com.jonas.domain.Role;
import com.jonas.domain.UserEntity;
import com.jonas.dto.UserRegistrationDto;
import com.jonas.repositories.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




/**
 *
 * @author Jonas, created 29/03/2021
 */
@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserEntity save(UserRegistrationDto registration){
        UserEntity users = new UserEntity();
        users.setFirstName(registration.getFirstName());
        users.setLastName(registration.getLastName());
        users.setEmail(registration.getEmail());
        users.setPassword(passwordEncoder.encode(registration.getPassword()));
        users.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(users);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity users = userRepository.findByEmail(email);
        if (users == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(users.getEmail(),
                users.getPassword(),
                mapRolesToAuthorities(users.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
    
}

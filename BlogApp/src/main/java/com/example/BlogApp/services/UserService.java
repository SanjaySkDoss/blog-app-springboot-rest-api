package com.example.BlogApp.services;

import com.example.BlogApp.models.Role;
import com.example.BlogApp.models.User;
import com.example.BlogApp.repositories.RoleRepository;
import com.example.BlogApp.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    RoleRepository roleRepository;
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository,UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(String name,String password,String email){
        User user = new User();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        Set<Role> authorities = new HashSet<>();
        authorities.add(roleRepository.findByName("ROLE_USER").orElseThrow(()-> new RuntimeException("Role not found")));
        user.setRoles(authorities);
        userRepository.save(user);
        return "User Created Successfully!!!";
    }

}

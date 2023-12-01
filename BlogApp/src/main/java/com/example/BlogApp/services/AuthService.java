package com.example.BlogApp.services;

import com.example.BlogApp.models.Role;
import com.example.BlogApp.models.User;
import com.example.BlogApp.repositories.RoleRepository;
import com.example.BlogApp.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    UserRepository userRepository;
    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,RoleRepository roleRepository,AuthenticationManager authenticationManager,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public String signup(String name,String password,String email){
        if(userRepository.existsByName(name))
            throw new RuntimeException("User already exists");
        if(userRepository.existsByEmail(email))
            throw new RuntimeException("Email already exists");
        userRepository.findByEmail(email);
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


    public String signin(String usernameOrEmail,String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User Logged-In Successfully";
    }
}

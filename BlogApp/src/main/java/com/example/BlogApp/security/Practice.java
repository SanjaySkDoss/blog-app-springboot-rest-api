package com.example.BlogApp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Practice {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("sanjay"));
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}

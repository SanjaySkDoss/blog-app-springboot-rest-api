package com.example.BlogApp.controllers;

import com.example.BlogApp.payloads.SignInDTO;
import com.example.BlogApp.payloads.SignUpDTO;
import com.example.BlogApp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = {"register","signup"})
    public ResponseEntity<String> signup(@RequestBody SignUpDTO signUpDTO){

        return new ResponseEntity<>(authService.signup(signUpDTO.getUsername(),signUpDTO.getPassword(),signUpDTO.getEmail()), HttpStatus.CREATED);
    }

    @PostMapping(value = {"signin","login"})
    public ResponseEntity<String> signin(@RequestBody SignInDTO signInDTO){

        return new ResponseEntity<>(authService.signin(signInDTO.getUsernameOrEmail(),signInDTO.getPassword()),HttpStatus.OK);
    }

}

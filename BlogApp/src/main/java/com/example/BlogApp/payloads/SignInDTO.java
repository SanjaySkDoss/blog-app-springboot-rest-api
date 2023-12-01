package com.example.BlogApp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {
    String usernameOrEmail;
    String password;
}

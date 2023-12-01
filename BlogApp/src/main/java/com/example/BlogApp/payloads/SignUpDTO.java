package com.example.BlogApp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    String username;
    String email;
    String password;
}

package com.example.BlogApp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
    Long id;
    String title;
    String description;
    String content;
}

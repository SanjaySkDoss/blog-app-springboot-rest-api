package com.example.BlogApp.payloads;

import com.example.BlogApp.models.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse {
    List<Post> content;
    int pageNo;
    int pageSize;
    int totalElements;
    int totalPages;
    boolean last;

}

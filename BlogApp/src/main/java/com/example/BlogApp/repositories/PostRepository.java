package com.example.BlogApp.repositories;

import com.example.BlogApp.models.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Override
    Page<Post> findAll(Pageable pageable);

    Post findByTitle(String title);

}

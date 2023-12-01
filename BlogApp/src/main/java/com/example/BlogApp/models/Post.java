package com.example.BlogApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post extends Base{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.LAZY)
    private Set<Comment> comments;
}

package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.ResourceNotFoundException;
import com.example.BlogApp.models.Post;
import com.example.BlogApp.payloads.PageResponse;
import com.example.BlogApp.payloads.PostRequestDTO;
import com.example.BlogApp.payloads.PostResponseDTO;
import com.example.BlogApp.services.PostService;
import com.example.BlogApp.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    PostService postService;
    ModelMapper modelMapper;

    public PostController(PostService postService,ModelMapper modelMapper){

        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO post){

        return new ResponseEntity<>(postService.createPost(post.getTitle(), post.getDescription(), post.getContent()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse> getAllPost(
            @RequestParam(value = "pgNo" , defaultValue = AppConstants.PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pgSize" , defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = AppConstants.SORT_DIR , required = false) String sortDir
            ){

        //return ResponseEntity.ok(postService.getAllPost());
        return ResponseEntity.ok(postService.getAllPost(pageNo,pageSize,sortBy,sortDir));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id,@RequestBody PostRequestDTO post) throws ResourceNotFoundException {

        return new ResponseEntity<>(postService.updatePost(id,modelMapper.map(post,Post.class)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.deletePost(id),HttpStatus.OK);
    }

}

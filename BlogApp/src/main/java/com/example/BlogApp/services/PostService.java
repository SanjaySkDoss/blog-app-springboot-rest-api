package com.example.BlogApp.services;

import com.example.BlogApp.exceptions.ResourceNotFoundException;
import com.example.BlogApp.models.Post;
import com.example.BlogApp.payloads.PageResponse;
import com.example.BlogApp.payloads.PostResponseDTO;
import com.example.BlogApp.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostService(PostRepository postRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PostResponseDTO createPost(String title,String description,String content){
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setContent(content);
        Post newPost = postRepository.save(post);

        PostResponseDTO postResponseDTO = modelMapper.map(newPost, PostResponseDTO.class);

        return postResponseDTO;
    }

    public PostResponseDTO getPost(Long id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post with the given ID: "+ id +" not found!!!"));
        return modelMapper.map(post, PostResponseDTO.class);
    }

    public PageResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir){
        //List<Post> post = postRepository.findAll();
        Sort sortDirection = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sortDirection);
        Page<Post> page = postRepository.findAll(pageable);

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(page.getContent());
        pageResponse.setPageNo(page.getNumber());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalElements((int)page.getTotalElements());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setLast(page.isLast());

//        List<Post> post = page.getContent();
//
//
//
//        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
//        for(Post p:post)
//                postResponseDTOList.add(modelMapper.map(p, PostResponseDTO.class));
        return pageResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(Long id,Post post) throws ResourceNotFoundException {
        Post oldPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post with the given ID: "+ id +" not found!!!"));
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setDescription(post.getDescription());
        //oldPost.setComments(post.getComments());
        postRepository.save(oldPost);
        return "Updated Post : "+oldPost.getId();

    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(Long id){
        postRepository.deleteById(id);
        return "Deleted Post Successfully";
    }



}

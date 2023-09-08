package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.Services.Implementations.AuthorPostServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/post")
public class AuthorPostControllers {
    @Autowired
    private AuthorPostServicesImpl authorPostServices;
    @PostMapping("/create-new-post")
    public ResponseEntity<AuthorPostResponse> createNewPost(@RequestBody AuthorPostRequest authorPostRequest){
        return new ResponseEntity<>(authorPostServices.createNewPost(authorPostRequest), HttpStatus.CREATED);
    }
    @PutMapping("/edit-published-blog")
    public ResponseEntity<AuthorPostResponse> updatePublishedBlog(@RequestBody AuthorPostRequest authorPostRequest){
        return new ResponseEntity<>(authorPostServices.updatePost(authorPostRequest),HttpStatus.ACCEPTED);
    }
    @GetMapping("/view-published-blog")
    public ResponseEntity<AuthorPostResponse> viewPublishedBlog(@RequestParam String blogId){
        return new ResponseEntity<>(authorPostServices.viewPost(blogId),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-blog/{blogId}")
    public ResponseEntity<String> removeBlogId(@PathVariable String blogId){
        return new ResponseEntity<>(authorPostServices.deletePost(blogId),HttpStatus.OK);
    }


}

package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.Services.Implementations.AuthorPostServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/post")
public class AuthorPostControllers {
    @Autowired
    private AuthorPostServicesImpl authorPostServices;
    @PostMapping("/create-new-post")
    public ResponseEntity<AuthorPostResponse> createNewPost(@RequestBody AuthorPostRequest authorPostRequest){
        return new ResponseEntity<>(authorPostServices.createNewPost(authorPostRequest), HttpStatus.CREATED);
    }
}

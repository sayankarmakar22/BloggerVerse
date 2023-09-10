package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.DTO.MoreContentTypeRequest;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Services.Implementations.AuthorPostServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/get-all-published-blog/{authorId}")
    public ResponseEntity<List<Map<String,Object>> > getAllPublishedBlog(@PathVariable String authorId){
        return new ResponseEntity<>(authorPostServices.getAllBlog(authorId),HttpStatus.FOUND);
    }
    @PostMapping("/add-more-content-type")
    public ResponseEntity<String> addContentType(@RequestBody MoreContentTypeRequest moreContentTypeRequest){
        return new ResponseEntity<>(authorPostServices.addMoreContentType(moreContentTypeRequest),HttpStatus.CREATED);
    }

}

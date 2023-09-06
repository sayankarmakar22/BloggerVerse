package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.AuthorRequest;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Services.Implementations.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;
    @PostMapping("/new-author")
    public ResponseEntity<AuthorResponse> createNewAuthor(@RequestBody AuthorRequest author){
        return new ResponseEntity<>(authorService.createNewAuthor(author), HttpStatus.CREATED);
    }
    @GetMapping("/get-author-details")
    public ResponseEntity<AuthorResponse> getAuthor(@RequestParam String id){
        return new ResponseEntity<>(authorService.viewAuthor(id), HttpStatus.FOUND);
    }
    @DeleteMapping("/remove-author/{id}")
    public ResponseEntity<String> removeAuthor(@PathVariable String id){
        return new ResponseEntity<>(authorService.deleteAuthor(id),HttpStatus.OK);
    }
    @PutMapping("/update-author-details")
    public ResponseEntity<AuthorResponse> updateAuthor(@RequestBody AuthorRequest authorRequest){
        return new ResponseEntity<>(authorService.updateAuthor(authorRequest),HttpStatus.ACCEPTED);
    }
}

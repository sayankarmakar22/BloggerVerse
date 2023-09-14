package com.sayan.BlogApplication.Helper;

import com.sayan.BlogApplication.DTO.AuthorRequest;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.ContentType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AuthorHelper {

    public static void setAuthorRequest(Author author,ContentType contentType, AuthorRequest authorRequest){
        author.setId(authorRequest.getId());
        author.setName(authorRequest.getName());
        author.setEmail(authorRequest.getEmail());
        author.setPhoneNumber(authorRequest.getPhoneNumber());
        author.setUsername(authorRequest.getUsername());
        author.setPassword(authorRequest.getPassword());
        author.setRole(authorRequest.getRole());
        author.setRegistrationDateTime(new Date());
        contentType.setContentType(authorRequest.getContentType());
        contentType.setAuthor(author);


    }
    public static AuthorResponse setAuthorResponse(AuthorResponse authorResponse, Author author, ContentType contentType){
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setUsername(author.getUsername());
        authorResponse.setEmail(author.getEmail());
        authorResponse.setRole(author.getRole());
        authorResponse.setPhoneNumber(author.getPhoneNumber());
        authorResponse.setRegistrationDateTime(String.valueOf(author.getRegistrationDateTime()));
        authorResponse.setContentType(contentType.getContentType());
        return authorResponse;
    }
}

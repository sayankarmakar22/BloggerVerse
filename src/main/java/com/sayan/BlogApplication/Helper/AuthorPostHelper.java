package com.sayan.BlogApplication.Helper;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Services.Implementations.ViewerResgisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthorPostHelper {
    public static void setPostDetailsRequest(AuthorPostRequest authorPostRequest, BlogPost blogPost, Author author,Author fetchedAuthor){
        author.setId(fetchedAuthor.getId());
        author.setName(fetchedAuthor.getName());
        author.setUsername(fetchedAuthor.getUsername());
        author.setRegistrationDateTime(fetchedAuthor.getRegistrationDateTime());
        author.setPassword(fetchedAuthor.getPassword());
        author.setEmail(fetchedAuthor.getEmail());
        author.setPhoneNumber(fetchedAuthor.getPhoneNumber());

        blogPost.setBlogId(authorPostRequest.getBlogId());
        blogPost.setBlogContent(authorPostRequest.getBlogContent());
        blogPost.setBlogTitle(authorPostRequest.getBlogTitle());
        blogPost.setBlogDateTime(new Date());
        blogPost.setAuthor(author);
    }
    public static AuthorPostResponse setPostDetailsResponse(AuthorPostResponse authorPostResponse,BlogPost post,long views,List<String> comments){
        authorPostResponse.setAuthorId(post.getAuthor().getId());
        authorPostResponse.setBlogId(post.getBlogId());
        authorPostResponse.setBlogContent(post.getBlogContent());
        authorPostResponse.setBlogTitle(post.getBlogTitle());
        authorPostResponse.setPublishDateTime(post.getBlogDateTime());
        authorPostResponse.setViews(views);
        authorPostResponse.setComments(comments);
        return authorPostResponse;
    }
}

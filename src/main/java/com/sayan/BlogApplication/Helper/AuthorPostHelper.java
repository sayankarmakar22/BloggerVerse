package com.sayan.BlogApplication.Helper;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthorPostHelper {
    public static void setPostDetailsRequest(AuthorPostRequest authorPostRequest, BlogPost blogPost, Author author){
        List<BlogPost> postList = new ArrayList<>();
//        author
        blogPost.setBlogId(authorPostRequest.getBlogId());
        blogPost.setBlogContent(authorPostRequest.getBlogContent());
        blogPost.setBlogTitle(authorPostRequest.getBlogTitle());
        blogPost.setBlogDateTime(new Date());
        postList.add(blogPost);
        author.setBlogPostList(postList);
    }
    public static AuthorPostResponse setPostDetailsResponse(AuthorPostResponse authorPostResponse,BlogPost blogPost,Author author){
        authorPostResponse.setAuthorId(author.getId());
        authorPostResponse.setBlogId(blogPost.getBlogId());
        authorPostResponse.setBlogTitle(blogPost.getBlogTitle());
        authorPostResponse.setBlogContent(blogPost.getBlogContent());
        authorPostResponse.setPublishDateTime(blogPost.getBlogDateTime());
        return authorPostResponse;
    }
}

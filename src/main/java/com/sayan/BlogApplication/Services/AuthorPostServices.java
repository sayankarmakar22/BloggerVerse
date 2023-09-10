package com.sayan.BlogApplication.Services;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.Model.BlogPost;

import java.util.List;
import java.util.Map;

public interface AuthorPostServices {
    AuthorPostResponse createNewPost(AuthorPostRequest authorPostRequest);
    AuthorPostResponse updatePost(AuthorPostRequest authorPostRequest);
    String deletePost(String blogId);
    AuthorPostResponse viewPost(String blogId);
    List<Map<String,Object>>  getAllBlog(String authorId);
}

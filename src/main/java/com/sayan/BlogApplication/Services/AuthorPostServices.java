package com.sayan.BlogApplication.Services;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;

public interface AuthorPostServices {
    AuthorPostResponse createNewPost(AuthorPostRequest authorPostRequest);
    AuthorPostResponse updatePost(AuthorPostRequest authorPostRequest);
    String deletePost(String blogId);
    AuthorPostResponse viewPost(String blogId);
}

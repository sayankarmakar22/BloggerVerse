package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Helper.AuthorPostHelper;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogComment;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.BlogView;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Repository.BlogPostRepo;
import com.sayan.BlogApplication.Repository.BlogViewRepo;
import com.sayan.BlogApplication.Services.AuthorPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuthorPostServicesImpl implements AuthorPostServices {
    @Autowired
    private BlogPostRepo blogPostRepo;
    @Autowired
    private BlogViewRepo blogViewRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Transactional
    @Override
    public AuthorPostResponse createNewPost(AuthorPostRequest authorPostRequest) {

        AuthorPostResponse authorPostResponse = new AuthorPostResponse();

        Author authorFromDb = authorRepo.findByid(authorPostRequest.getAuthorId());

        BlogPost blogPost = new BlogPost();

        blogPost.setBlogId(authorPostRequest.getBlogId());
        blogPost.setBlogContent(authorPostRequest.getBlogContent());
        blogPost.setBlogTitle(authorPostRequest.getBlogTitle());
        blogPost.setBlogDateTime(new Date());
        blogPost.setAuthor(authorFromDb);

        BlogPost save = blogPostRepo.save(blogPost);

        List<BlogPost> postList = new ArrayList<>();
        postList.add(save);
        
        authorFromDb.setBlogPostList(postList);

        authorPostResponse.setAuthorId(authorFromDb.getId());
        authorPostResponse.setBlogId(blogPost.getBlogId());
        authorPostResponse.setBlogTitle(blogPost.getBlogTitle());
        authorPostResponse.setBlogContent(blogPost.getBlogContent());
        authorPostResponse.setPublishDateTime(blogPost.getBlogDateTime());
        return authorPostResponse;
    }

    @Override
    public AuthorPostResponse updatePost(AuthorPostRequest authorPostRequest) {
        return null;
    }

    @Override
    public String deletePost(int blogId) {
        return null;
    }

    @Override
    public AuthorPostResponse viewPost(int blogId) {
        return null;
    }

}

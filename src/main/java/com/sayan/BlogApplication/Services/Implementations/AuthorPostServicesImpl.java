package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Helper.AuthorHelper;
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
    public long getBlogViews(String blogId){
        return authorRepo.totalViews(blogId);
    }
    @Transactional
    @Override
    public AuthorPostResponse createNewPost(AuthorPostRequest authorPostRequest) {
        if(authorRepo.existsById(authorPostRequest.getAuthorId())){
            Author author = new Author();
            BlogPost blogPost = new BlogPost();
            AuthorPostResponse authorPostResponse = new AuthorPostResponse();

            Author fetchedAuthor = authorRepo.findByid(authorPostRequest.getAuthorId());
            AuthorPostHelper.setPostDetailsRequest(authorPostRequest,blogPost,author,fetchedAuthor);
            BlogPost post = blogPostRepo.save(blogPost);
            return AuthorPostHelper.setPostDetailsResponse(authorPostResponse,post,getBlogViews(post.getBlogId()));
        }
        throw new RuntimeException("user not exists");
    }

    @Override
    public AuthorPostResponse updatePost(AuthorPostRequest authorPostRequest) {
        BlogPost blogPost = new BlogPost();
        AuthorPostResponse authorPostResponse = new AuthorPostResponse();
        BlogPost foundPublishedBlogFromDb = blogPostRepo.findByblogId(authorPostRequest.getBlogId());
        foundPublishedBlogFromDb.setBlogTitle(authorPostRequest.getBlogTitle());
        foundPublishedBlogFromDb.setBlogContent(authorPostRequest.getBlogContent());
        BlogPost editedBlogContent = blogPostRepo.save(foundPublishedBlogFromDb);
        AuthorPostHelper.setPostDetailsResponse(authorPostResponse,editedBlogContent,getBlogViews(authorPostRequest.getBlogId()));
        return authorPostResponse;
    }

    @Override
    public String deletePost(String blogId) {
        blogPostRepo.deleteById(blogId);
        return blogId + " has been deleted successfully !!!!";
    }

    @Override
    public AuthorPostResponse viewPost(String blogId) {
        BlogPost foundPublishedBlog = blogPostRepo.findByblogId(blogId);
        AuthorPostResponse authorPostResponse = new AuthorPostResponse();
        AuthorPostResponse response = AuthorPostHelper.setPostDetailsResponse(authorPostResponse, foundPublishedBlog,getBlogViews(blogId));
        return response;
    }

}

package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.AuthorPostRequest;
import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.DTO.MoreContentTypeRequest;
import com.sayan.BlogApplication.Helper.AuthorHelper;
import com.sayan.BlogApplication.Helper.AuthorPostHelper;
import com.sayan.BlogApplication.Helper.ViewerHelper;
import com.sayan.BlogApplication.Model.*;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Repository.BlogPostRepo;
import com.sayan.BlogApplication.Repository.BlogViewRepo;
import com.sayan.BlogApplication.Repository.ContentTypeRepo;
import com.sayan.BlogApplication.Services.AuthorPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.invoke.VarHandle;
import java.util.*;

@Service
public class AuthorPostServicesImpl implements AuthorPostServices {
    @Autowired
    private BlogPostRepo blogPostRepo;
    @Autowired
    private BlogViewRepo blogViewRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private ContentTypeRepo contentTypeRepo;

    public long getBlogViews(String blogId){
        return authorRepo.totalViews(blogId);
    }
    public List<String> getAllComments(String blogId){
        return authorRepo.getAllBlogComments(blogId);
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
            return AuthorPostHelper.setPostDetailsResponse(authorPostResponse,post,0,getAllComments(post.getBlogId()));
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
        AuthorPostResponse response;
        try{
            long views = getBlogViews(authorPostResponse.getBlogId());
            response = AuthorPostHelper.setPostDetailsResponse(authorPostResponse,editedBlogContent,views,getAllComments(authorPostRequest.getBlogId()));
        }
        catch (NullPointerException e){
            response = AuthorPostHelper.setPostDetailsResponse(authorPostResponse,editedBlogContent,0,getAllComments(authorPostRequest.getBlogId()));
        }
        return response;
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
        AuthorPostResponse response;
        try{
            long views = getBlogViews(blogId);
            response = AuthorPostHelper.setPostDetailsResponse(authorPostResponse, foundPublishedBlog,views,getAllComments(blogId));
        }
        catch (NullPointerException e){
            response = AuthorPostHelper.setPostDetailsResponse(authorPostResponse, foundPublishedBlog,0l,getAllComments(blogId));
        }

        return response;
    }

    @Override
    public List<Map<String,Object>>  getAllBlog(String authorId) {
        List<Map<String, Object>> allBlog = authorRepo.getAllBlog(authorId);
        if(allBlog.size() > 0){
            return allBlog;
        }
        else{
            List<Map<String,Object>> notFoundBlogResponse = new ArrayList<>();
            Map<String,Object> blog = new HashMap<>();
            blog.put("number of blog published : ",0);
            notFoundBlogResponse.add(0,blog);
            return notFoundBlogResponse;
        }
    }

    @Override
    public String addMoreContentType(MoreContentTypeRequest moreContentTypeRequest) {
        try{
            Author savedAuthor = authorRepo.findByid(moreContentTypeRequest.getAuthorId());
            Author author = new Author();
            ViewerHelper.setAuthor(author,savedAuthor);
            ContentType contentType = new ContentType();
            contentType.setContentType(moreContentTypeRequest.getContentType());
            contentType.setAuthor(author);
            contentTypeRepo.save(contentType);
            return "added content type:" + moreContentTypeRequest.getContentType();
        }catch (Exception e ){
            return "you have tried to add content type to an non-existing author";
        }
    }

}

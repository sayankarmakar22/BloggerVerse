package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.*;
import com.sayan.BlogApplication.Helper.ViewerHelper;
import com.sayan.BlogApplication.Model.*;
import com.sayan.BlogApplication.Repository.*;
import com.sayan.BlogApplication.Services.ViewerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ViewerResgisterServiceImpl implements ViewerServices {
    private Logger logger = LoggerFactory.getLogger(ViewerResgisterServiceImpl.class);
    @Autowired
    private ViewerRepo viewerRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BlogViewRepo blogViewRepo;
    @Autowired
    private BlogPostRepo blogPostRepo;
    @Autowired
    private BlogCommentRepo blogCommentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long getBlogViews(String blogId){
        Long views = blogViewRepo.totalViews(blogId);
        return views;
    }
    public List<String> getAllComments(String blogId){
        List<String> allBlogComments = blogCommentRepo.getAllBlogComments(blogId);
        return allBlogComments;
    }
    @Override
    public ViewerRegisterResponse registerViewer(ViewerRegisterRequest viewerRegisterRequest){
        viewerRegisterRequest.setPassword(passwordEncoder.encode(viewerRegisterRequest.getPassword()));
        Viewer viewer = new Viewer();
        ViewerRegisterResponse viewerRegisterResponse = new ViewerRegisterResponse();
        ViewerHelper.setViewerRegistrationDetails(viewerRegisterRequest,viewer);
        Viewer savedViewerDetails = viewerRepo.save(viewer);
        return ViewerHelper.setViewerRegistrationDetailResponse(savedViewerDetails,viewerRegisterResponse);
    }

    @Override
    public ViewerRegisterResponse getViewerDeatils(String viewerId) {
        ViewerRegisterResponse viewerRegisterResponse = new ViewerRegisterResponse();
        Viewer foundViewerFromDb = viewerRepo.findByviewerId(viewerId);
        return ViewerHelper.setViewerRegistrationDetailResponse(foundViewerFromDb,viewerRegisterResponse);
    }

    @Override
    public ViewerRegisterResponse updateViewerDetails(ViewerRegisterRequest viewerRegisterRequest) {
        if(viewerRepo.existsByviewerId(viewerRegisterRequest.getViewerId())){
            Viewer viewer = new Viewer();
            ViewerRegisterResponse viewerRegisterResponse = new ViewerRegisterResponse();
            Viewer viewerGetFromDb = viewerRepo.findByviewerId(viewerRegisterRequest.getViewerId());
            viewerGetFromDb.setName(viewerRegisterRequest.getName());
            viewerGetFromDb.setEmail(viewerRegisterRequest.getEmail());
            viewerGetFromDb.setUsername(viewerRegisterRequest.getUsername());
            viewerGetFromDb.setPassword(viewerRegisterRequest.getPassword());
            Viewer saveUpdatedViewerDetails = viewerRepo.save(viewerGetFromDb);
            ViewerHelper.setViewerRegistrationDetailResponse(saveUpdatedViewerDetails,viewerRegisterResponse);
            return viewerRegisterResponse;
        }
        throw new RuntimeException("you have tried to updated an non-existing user details");
    }

    @Override
    public String deleteViewerAccount(String viewerId) {
        viewerRepo.deleteById(viewerId);
        return viewerId + " has been successfully deleted";
    }

    @Override
    public BlogViewResponse viewBlogAndUpdatedViewsToDb(String viewSerialId,String blogId,String viewerId) {
        BlogPost publishedBlog = blogPostRepo.findByblogId(blogId); //get published blog from db
        Author savedAuthor = authorRepo.findByid(publishedBlog.getAuthor().getId()); // get the saved author from db

        Author author = new Author();
        ViewerHelper.setAuthor(author,savedAuthor);


        BlogPost blogPost = new BlogPost();
        ViewerHelper.setBlogPost(blogPost,publishedBlog,savedAuthor);

            BlogView blogView = new BlogView();
            blogView.setViewSerialId(viewSerialId);
            blogView.setViewerId(viewerId);
            blogView.setAuthorId(savedAuthor.getId());
            blogView.setViewCount(1l);
            blogView.setBlogId(blogPost);
            BlogView savedBlog = blogViewRepo.save(blogView);

            BlogViewResponse blogViewResponse = new BlogViewResponse();
            blogViewResponse.setAuthorName(savedAuthor.getName());
            blogViewResponse.setBlogTitle(blogPost.getBlogTitle());
            blogViewResponse.setBlogContent(blogPost.getBlogContent());
            blogViewResponse.setBlogViews(getBlogViews(blogId));
            blogViewResponse.setBlogComments(getAllComments(blogId));
            return blogViewResponse;
    }

    @Override
    public String commentOnBlog(CommentBlogRequest commentBlogRequest) {
        BlogPost publishedBlog = blogPostRepo.findByblogId(commentBlogRequest.getBlogId()); //get published blog from db
        Author savedAuthor = authorRepo.findByid(commentBlogRequest.getAuthorId());
        Author author = new Author();
        ViewerHelper.setAuthor(author,savedAuthor);

        BlogPost blogPost = new BlogPost();
        ViewerHelper.setBlogPost(blogPost,publishedBlog,savedAuthor);

        BlogComment blogComment = new BlogComment();

        blogComment.setBlogSerialNumber(commentBlogRequest.getCommentId());
        blogComment.setViewerId(commentBlogRequest.getViewerId());
        blogComment.setComments(commentBlogRequest.getComment());
        blogComment.setCommentDateTime(new Date());
        blogComment.setBlogId(blogPost);

        blogCommentRepo.save(blogComment);
        return "comment added!!!";
    }
}

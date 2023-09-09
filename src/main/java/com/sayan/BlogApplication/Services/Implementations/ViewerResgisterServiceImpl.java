package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.AuthorPostResponse;
import com.sayan.BlogApplication.DTO.BlogViewResponse;
import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
import com.sayan.BlogApplication.Helper.ViewerHelper;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.BlogView;
import com.sayan.BlogApplication.Model.Viewer;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Repository.BlogPostRepo;
import com.sayan.BlogApplication.Repository.BlogViewRepo;
import com.sayan.BlogApplication.Repository.ViewerRepo;
import com.sayan.BlogApplication.Services.ViewerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViewerResgisterServiceImpl implements ViewerServices {
    @Autowired
    private ViewerRepo viewerRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BlogViewRepo blogViewRepo;
    @Autowired
    private BlogPostRepo blogPostRepo;

    public long getBlogViews(String blogId){
        Long views = blogViewRepo.totalViews(blogId);
        return views;
    }
    @Override
    public ViewerRegisterResponse registerViewer(ViewerRegisterRequest viewerRegisterRequest){
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
        BlogPost publishedBlog = blogPostRepo.findByblogId(blogId);
        Viewer savedViewer = viewerRepo.findByviewerId(viewerId); //get viewer details from db
        Author savedAuthor = authorRepo.findByid(publishedBlog.getAuthor().getId()); // get the saved author from db

        Author author = new Author();
        author.setId(savedAuthor.getId());
        author.setName(savedAuthor.getName());
        author.setRegistrationDateTime(savedAuthor.getRegistrationDateTime());
        author.setUsername(savedAuthor.getUsername());
        author.setPassword(savedAuthor.getPassword());
        author.setEmail(savedAuthor.getEmail());
        author.setPhoneNumber(savedAuthor.getPhoneNumber());

//        Viewer viewer = new Viewer();
//        viewer.setViewerId(savedViewer.getViewerId());
//        viewer.setName(savedViewer.getName());
//        viewer.setEmail(savedViewer.getEmail());
//        viewer.setUsername(savedViewer.getUsername());
//        viewer.setPassword(savedViewer.getPassword());
//        viewer.setRegistrationDateTime(savedViewer.getRegistrationDateTime());

        BlogPost blogPost = new BlogPost();
        blogPost.setBlogId(publishedBlog.getBlogId());
        blogPost.setBlogContent(publishedBlog.getBlogContent());
        blogPost.setBlogTitle(publishedBlog.getBlogTitle());
        blogPost.setBlogDateTime(publishedBlog.getBlogDateTime());
        blogPost.setAuthor(savedAuthor);

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
//            long totalViews = blogViewRepo.totalViews(blogId);
            blogViewResponse.setBlogViews(getBlogViews(blogId));
            return blogViewResponse;
    }
}

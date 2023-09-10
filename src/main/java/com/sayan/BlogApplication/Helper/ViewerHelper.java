package com.sayan.BlogApplication.Helper;

import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.Viewer;

import java.util.Date;

public class ViewerHelper {
    public static void setViewerRegistrationDetails(ViewerRegisterRequest viewerRegisterRequest, Viewer viewer){
        viewer.setViewerId(viewerRegisterRequest.getViewerId());
        viewer.setName(viewerRegisterRequest.getName());
        viewer.setEmail(viewerRegisterRequest.getEmail());
        viewer.setUsername(viewerRegisterRequest.getUsername());
        viewer.setPassword(viewerRegisterRequest.getPassword());
        viewer.setRegistrationDateTime(new Date());
    }
    public static ViewerRegisterResponse setViewerRegistrationDetailResponse(Viewer viewer,ViewerRegisterResponse viewerRegisterResponse){
        viewerRegisterResponse.setViewerId(viewer.getViewerId());
        viewerRegisterResponse.setName(viewer.getName());
        viewerRegisterResponse.setEmail(viewer.getEmail());
        viewerRegisterResponse.setUsername(viewer.getUsername());
        viewerRegisterResponse.setRegistrationDateTime(viewer.getRegistrationDateTime());
        return viewerRegisterResponse;
    }
    public static void setAuthor(Author author,Author savedAuthor){
        author.setId(savedAuthor.getId());
        author.setName(savedAuthor.getName());
        author.setRegistrationDateTime(savedAuthor.getRegistrationDateTime());
        author.setUsername(savedAuthor.getUsername());
        author.setPassword(savedAuthor.getPassword());
        author.setEmail(savedAuthor.getEmail());
        author.setPhoneNumber(savedAuthor.getPhoneNumber());
    }
    public static void setBlogPost(BlogPost blogPost,BlogPost publishedBlog,Author savedAuthor){
        blogPost.setBlogId(publishedBlog.getBlogId());
        blogPost.setBlogContent(publishedBlog.getBlogContent());
        blogPost.setBlogTitle(publishedBlog.getBlogTitle());
        blogPost.setBlogDateTime(publishedBlog.getBlogDateTime());
        blogPost.setAuthor(savedAuthor);
    }
}

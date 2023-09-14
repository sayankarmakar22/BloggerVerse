package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.BlogViewResponse;
import com.sayan.BlogApplication.DTO.CommentBlogRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
import com.sayan.BlogApplication.Model.Viewer;
import com.sayan.BlogApplication.Services.Implementations.ViewerResgisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;

@RestController
@RequestMapping("/blog/viewer")
public class ViewerControllers {
    @Autowired
    private ViewerResgisterServiceImpl viewerResgisterService;

    @PostMapping("/register-viewer")
    public ResponseEntity<ViewerRegisterResponse> registerViewer(@RequestBody ViewerRegisterRequest viewerRegisterRequest){
        return new ResponseEntity<>(viewerResgisterService.registerViewer(viewerRegisterRequest), HttpStatus.CREATED);
    }
    @GetMapping("/get-viewer-details")
    @Secured({"viewer"})
    public ResponseEntity<ViewerRegisterResponse> getViewerDetails(@RequestParam String viewerId){
        return new ResponseEntity<>(viewerResgisterService.getViewerDeatils(viewerId),HttpStatus.FOUND);
    }
    @PutMapping("/updated-viewer-details")
    @Secured({"viewer"})
    public ResponseEntity<ViewerRegisterResponse> updateViewerDetails(@RequestBody ViewerRegisterRequest viewerRegisterRequest){
        return new ResponseEntity<>(viewerResgisterService.updateViewerDetails(viewerRegisterRequest), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-viewer-account/{viewerId}")
    @Secured({"viewer"})
    public ResponseEntity<String> deleteAccountViewer(@PathVariable String viewerId){
        return new ResponseEntity<>(viewerResgisterService.deleteViewerAccount(viewerId),HttpStatus.OK);
    }
    @GetMapping("/view-post/{viewSerialId}/{viewerId}/{blogId}")
    @Secured({"viewer"})
    public ResponseEntity<BlogViewResponse> viewBlog(@PathVariable String viewSerialId,@PathVariable String blogId,@PathVariable String viewerId){
        return new ResponseEntity<>(viewerResgisterService.viewBlogAndUpdatedViewsToDb(viewSerialId,blogId,viewerId),HttpStatus.OK);
    }
    @PostMapping("/comment-blog")
    @Secured({"viewer"})
    public ResponseEntity<String> commentOnBlog(@RequestBody CommentBlogRequest commentBlogRequest){
        return new ResponseEntity<>(viewerResgisterService.commentOnBlog(commentBlogRequest),HttpStatus.CREATED);
    }
}

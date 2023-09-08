package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
import com.sayan.BlogApplication.Helper.ViewerHelper;
import com.sayan.BlogApplication.Model.Viewer;
import com.sayan.BlogApplication.Repository.ViewerRepo;
import com.sayan.BlogApplication.Services.ViewerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewerResgisterServiceImpl implements ViewerServices {
    @Autowired
    private ViewerRepo viewerRepo;
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
}

package com.sayan.BlogApplication.Helper;

import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
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
}

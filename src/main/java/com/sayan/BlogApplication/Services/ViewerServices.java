package com.sayan.BlogApplication.Services;

import com.sayan.BlogApplication.DTO.ViewerRegisterRequest;
import com.sayan.BlogApplication.DTO.ViewerRegisterResponse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ViewerServices {
    ViewerRegisterResponse registerViewer(ViewerRegisterRequest viewerRegisterRequest);
    ViewerRegisterResponse getViewerDeatils(String viewerId);
    ViewerRegisterResponse updateViewerDetails(ViewerRegisterRequest viewerRegisterRequest);
    String deleteViewerAccount(String viewerId);
}

package com.blueprint.image.recognition.web.config;

import com.http.paths.ApiRouteConstants;
import org.springframework.stereotype.Component;

@Component
public class RouteConstantsBean {
    public String getView() {
        return ApiRouteConstants.VIEW;
    }
    public String getViewImages() {
        return ApiRouteConstants.ImageRecognition.VIEW_IMAGES;
    }
    public String getViewImagesUpload() {
        return ApiRouteConstants.ImageRecognition.VIEW_IMAGES_UPLOAD;
    }
}

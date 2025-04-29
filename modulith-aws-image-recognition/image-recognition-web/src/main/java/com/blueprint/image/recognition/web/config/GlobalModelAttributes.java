package com.blueprint.image.recognition.web.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {
    @ModelAttribute("routes")
    public RouteConstantsBean routes() {
        return new RouteConstantsBean();
    }
}

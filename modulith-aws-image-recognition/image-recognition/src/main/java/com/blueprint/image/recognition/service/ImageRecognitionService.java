package com.blueprint.image.recognition.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageRecognitionService {
    List<String> searchImages(@RequestParam String keyword);
    void saveImage(String key, MultipartFile file);
}

package com.blueprint.image.recognition.input;

import com.blueprint.image.recognition.service.ImageRecognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.http.paths.ApiRouteConstants.*;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class ImageRecognitionController {

    private final ImageRecognitionService imageRecognitionService;

    @PostMapping(IMAGE_RECOGNITION_UPLOAD)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String key = file.getOriginalFilename();
        imageRecognitionService.saveImage(key, file);
        return ResponseEntity.ok("File uploaded successfully: " + key);
    }

    @PostMapping(IMAGE_RECOGNITION_SEARCH)
    public ResponseEntity<List<String>> searchForImages(@PathVariable("key") String keyword) {
        return ResponseEntity.ok(imageRecognitionService.searchImages(keyword));
    }
}

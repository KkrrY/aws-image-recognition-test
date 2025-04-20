package com.blueprint.aws.s3.input;

import com.blueprint.aws.s3.service.ImageS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import static com.http.paths.ApiRouteConstants.*;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class S3Controller {
    private final ImageS3Service s3Service;

    @PostMapping(S3_UPLOAD)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String key = file.getOriginalFilename();
        s3Service.uploadFile(key, file);
        return ResponseEntity.ok("File uploaded successfully: " + key);
    }

    @PostMapping(S3_GENERATE_URL)
    public ResponseEntity<String> generatePresignedURL(@PathVariable("key") String filename) {
        String url = s3Service.generatePresignedUrl(filename);
        return ResponseEntity.ok(url);
    }

    @GetMapping(S3_DOWNLOAD)
    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
        byte[] data = s3Service.downloadFile(key);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @DeleteMapping(S3_DELETE)
    public ResponseEntity<String> deleteFile(@PathVariable("key") String key) {
        s3Service.deleteFile(key);
        return ResponseEntity.ok("Successfully deleted file with name: " + key);
    }

    @GetMapping(S3_ALL)
    public ResponseEntity<List<String>> getAllFiles () {
        return ResponseEntity.ok(s3Service.generatePresignedUrlsForAllFiles());
    }

}

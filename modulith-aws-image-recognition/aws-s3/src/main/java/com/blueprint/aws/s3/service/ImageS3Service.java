package com.blueprint.aws.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;

public interface ImageS3Service {
    void uploadFile(String key, MultipartFile file);
    void deleteFile(String key);
    byte[] downloadFile(String key);
    String generatePresignedUrl(String key);
    List<String> generatePresignedUrlsForAllFiles();
}

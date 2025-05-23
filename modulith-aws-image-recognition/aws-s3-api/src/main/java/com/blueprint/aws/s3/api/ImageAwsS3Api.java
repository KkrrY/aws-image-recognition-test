package com.blueprint.aws.s3.api;

import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;

public interface ImageAwsS3Api {
    void uploadFile(String key, MultipartFile file);
    void deleteFile(String key);
    byte[] downloadFile(String key);
    String generatePresignedUrl(String key);
    List<String> generatePresignedUrlsForAllFiles();
}

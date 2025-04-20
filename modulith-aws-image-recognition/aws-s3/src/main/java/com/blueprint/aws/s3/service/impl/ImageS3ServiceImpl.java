package com.blueprint.aws.s3.service.impl;

import com.blueprint.aws.s3.api.ImageAwsS3Api;
import com.blueprint.aws.s3.service.ImageS3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImageS3ServiceImpl implements ImageS3Service, ImageAwsS3Api {
    private static final int URL_DURATION_MINUTES = 10;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${application.image.bucket.name}")
    private String fileBucketName;

    @Override
    @SneakyThrows
    public void uploadFile(String key, MultipartFile file) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(fileBucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
    }

    @Override
    public byte[] downloadFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(fileBucketName)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }

    @Override
    public void deleteFile(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(fileBucketName)
                .key(key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }

    @Override
    public String generatePresignedUrl(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(fileBucketName)
                .key(key)
                .build();
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(URL_DURATION_MINUTES))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        presignedRequest.isBrowserExecutable();
        return presignedRequest.url().toString();
    }

    @Override
    public List<String> generatePresignedUrlsForAllFiles() {
        ListObjectsV2Request listObjects = ListObjectsV2Request.builder()
                .bucket(fileBucketName)
                .build();

        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjects);
        return listObjectsResponse.contents().stream()
                .map(S3Object::key)
                .map(this::generatePresignedUrl)
                .collect(Collectors.toList());
    }
}


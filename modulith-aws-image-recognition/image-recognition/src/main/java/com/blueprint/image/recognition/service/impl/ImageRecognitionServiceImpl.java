package com.blueprint.image.recognition.service.impl;

import com.blueprint.aws.rekognition.api.AwsRekognitionApi;
import com.blueprint.aws.s3.api.ImageAwsS3Api;
import com.blueprint.common.model.dto.aws.rekognition.ModerationLabelDto;
import com.blueprint.image.recognition.model.domain.ImageMetadata;
import com.blueprint.image.recognition.output.persistence.ImageMetaDataRepository;
import com.blueprint.image.recognition.service.ImageRecognitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageRecognitionServiceImpl implements ImageRecognitionService {

    private final ImageAwsS3Api awsS3Api;
    private final AwsRekognitionApi awsRekognitionApi;
    private final ImageMetaDataRepository imageMetaDataRepository;

    @Override
    public List<String> searchImages(String keyword) {
        keyword = normalize(keyword);
        return imageMetaDataRepository.findAllByLabel(keyword).stream()
                .map(ImageMetadata::getFileName)
                .map(fileName -> awsS3Api.generatePresignedUrl(fileName))
                .toList();
    }

    @Override
    @Transactional
    public void saveImage(String key, MultipartFile file) {
        awsS3Api.uploadFile(key, file);
        List<String> labels = awsRekognitionApi
                .detectLabels(file)
                .getLabels()
                .stream()
                .map(ModerationLabelDto::getName)
                .map(item -> item.toLowerCase())
                .toList();

        Optional<ImageMetadata> existing = imageMetaDataRepository.findByFileName(key);

        ImageMetadata metadata = existing
                .map(meta -> {
                    meta.getLabels().clear();
                    meta.getLabels().addAll(labels);
                    return meta;
                })
                .orElseGet(() -> ImageMetadata.builder()
                        .fileName(key)
                        .labels(labels)
                        .build());

        imageMetaDataRepository.save(metadata);
    }

    private String normalize(String input) {
        return input.toLowerCase()
                .replaceAll("(s|es|ing)$", "")
                .trim();
    }
}

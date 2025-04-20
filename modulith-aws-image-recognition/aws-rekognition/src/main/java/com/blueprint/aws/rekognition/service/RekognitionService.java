package com.blueprint.aws.rekognition.service;

import com.blueprint.common.model.dto.aws.rekognition.AwsModerationLabelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RekognitionService {
    AwsModerationLabelResponse detectLabels(MultipartFile imageToCheck);
}

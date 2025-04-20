package com.blueprint.aws.rekognition.api;

import com.blueprint.common.model.dto.aws.rekognition.AwsModerationLabelResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AwsRekognitionApi {
    AwsModerationLabelResponse detectLabels(MultipartFile imageToCheck);
}

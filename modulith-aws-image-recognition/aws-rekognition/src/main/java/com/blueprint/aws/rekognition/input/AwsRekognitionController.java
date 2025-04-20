package com.blueprint.aws.rekognition.input;

import com.blueprint.aws.rekognition.service.RekognitionService;
import com.blueprint.common.model.dto.aws.rekognition.AwsModerationLabelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import static com.http.paths.ApiRouteConstants.API_V1;
import static com.http.paths.ApiRouteConstants.AWS_REKOGNITION_IDENTIFY;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1)
public class AwsRekognitionController {

    private final RekognitionService rekognitionService;

    @PostMapping(AWS_REKOGNITION_IDENTIFY)
    public ResponseEntity<AwsModerationLabelResponse> detectModerationLabels(@RequestParam("file") MultipartFile image) {
        return ResponseEntity.ok(rekognitionService.detectLabels(image));
    }
}

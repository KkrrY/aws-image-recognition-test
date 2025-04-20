package com.blueprint.aws.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.blueprint.aws.rekognition.api.AwsRekognitionApi;
import com.blueprint.aws.rekognition.service.RekognitionService;
import com.blueprint.common.model.dto.aws.rekognition.ModerationLabelDto;
import com.blueprint.common.model.dto.aws.rekognition.AwsModerationLabelResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RekognitionServiceImpl implements RekognitionService, AwsRekognitionApi {

    private final AmazonRekognition client;

    @SneakyThrows
    public AwsModerationLabelResponse detectLabels(MultipartFile imageToCheck) {
        DetectLabelsResult result = detectModerationLabels(imageToCheck);
        List<ModerationLabelDto> labels = result.getLabels().stream()
                .map(label -> new ModerationLabelDto(
                        label.getName(),
                        label.getConfidence()
                ))
                .collect(Collectors.toList());

        return new AwsModerationLabelResponse(labels);
    }

    private DetectLabelsResult detectModerationLabels(MultipartFile imageToCheck) throws IOException {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(imageToCheck.getBytes())));

        return client.detectLabels(request);
    }

}

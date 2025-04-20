package com.blueprint.common.model.dto.aws.rekognition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwsModerationLabelResponse {
    private List<ModerationLabelDto> labels;
}

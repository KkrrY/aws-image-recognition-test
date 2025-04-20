package com.blueprint.common.model.dto.aws.rekognition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModerationLabelDto {
    private String name;
    private Float confidence;
}

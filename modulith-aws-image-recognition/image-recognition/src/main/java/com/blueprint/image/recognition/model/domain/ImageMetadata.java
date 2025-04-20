package com.blueprint.image.recognition.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImageMetadata {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;

    @ElementCollection
    private List<String> labels;
}


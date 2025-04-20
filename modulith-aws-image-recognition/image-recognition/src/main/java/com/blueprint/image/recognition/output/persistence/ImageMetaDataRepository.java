package com.blueprint.image.recognition.output.persistence;

import com.blueprint.image.recognition.model.domain.ImageMetadata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImageMetaDataRepository extends CrudRepository<ImageMetadata, String> {
    @Query("SELECT i FROM ImageMetadata i JOIN i.labels l WHERE l = :keyword")
    List<ImageMetadata> findAllByLabel(String keyword);

    Optional<ImageMetadata> findByFileName(String fileName);
}

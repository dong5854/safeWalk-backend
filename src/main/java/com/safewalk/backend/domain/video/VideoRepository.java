package com.safewalk.backend.domain.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, String> {
    VideoEntity findByFilename(String filename);
}

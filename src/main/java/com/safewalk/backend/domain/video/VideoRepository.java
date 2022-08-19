package com.safewalk.backend.domain.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, String> {
    VideoEntity findVideoEntitiesByFileUUID(String fileUUID);
    List<VideoEntity> findVideoEntitiesByOriginalFilenameIgnoreCase(String fileName);
}

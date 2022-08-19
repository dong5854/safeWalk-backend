package com.safewalk.backend.domain.video;

import com.safewalk.backend.config.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StorageProperties storageProperties;
    private final VideoRepository videoRepository;

    @Transactional
    public String store(MultipartFile file) {
        String fileUUID = UUID.randomUUID().toString();
        String filePath = storageProperties.getLocation() + fileUUID;
        VideoEntity videoEntity = new VideoEntity(fileUUID, filePath, file.getOriginalFilename());
        try {
            videoRepository.save(videoEntity);
            file.transferTo(new File(storageProperties.getLocation() + fileUUID));
        } catch (IOException | IllegalStateException e){
            e.printStackTrace();
            System.out.println(e);
            return "저장 실패";
        }

        return file.getOriginalFilename() + " saved as " + fileUUID;
    }

    public List<VideoEntity> findByOriginalName(String originalName){
        return videoRepository.findVideoEntitiesByOriginalFilenameIgnoreCase(originalName);
    }

    public VideoEntity findByUUID(String fileUUID){
        return videoRepository.findVideoEntitiesByFileUUID(fileUUID);
    }
}

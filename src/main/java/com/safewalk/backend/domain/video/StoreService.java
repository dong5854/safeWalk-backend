package com.safewalk.backend.domain.video;

import com.safewalk.backend.config.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StorageProperties storageProperties;

    public String store(MultipartFile file) {
        try {
            file.transferTo(new File(storageProperties.getLocation() + file.getOriginalFilename()));
            System.out.println(storageProperties.getLocation() + file.getOriginalFilename());
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(e);
            return "저장 실패";
        } catch (IllegalStateException e){
            e.printStackTrace();
            System.out.println(e);
            return "저장 실패";
        }

        return file.getOriginalFilename() + " 저장 성공";
    }
}

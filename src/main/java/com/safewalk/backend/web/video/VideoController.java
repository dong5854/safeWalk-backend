package com.safewalk.backend.web.video;

import com.safewalk.backend.domain.video.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
public class VideoController {

    private final StoreService storeService;

    @PostMapping(value = "/video")
    public String PostVideo(@RequestParam("video")MultipartFile video) {
        return storeService.store(video);
    }
}

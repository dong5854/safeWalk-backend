package com.safewalk.backend.web.video;

import com.safewalk.backend.domain.video.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class VideoController {

    private final StoreService storeService;

    @PostMapping(value = "/video")
    public String PostVideo(@RequestParam("video")MultipartFile video) {
        return storeService.store(video);
    }

    @GetMapping(value = "/video/originalFileName/{originalFileName}")
    public List<String> FindByOriginalFilename(@PathVariable String originalFileName){
        List<String> result = new ArrayList<>();
        storeService.findByOriginalName(originalFileName).stream()
                .forEach(s ->result.add("original name: " + s.getOriginalFilename() + "uuid: " + s.getFileUUID()));
        return result;
    }

    @GetMapping(value = "/video/uuid/{uuid}")
    public String FindVideoByUUID(@PathVariable String uuid){
        return storeService.findByUUID(uuid).toString();
    }

    @GetMapping(value = "/video/download/uuid/{uuid}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity DownloadVideoByUUID(@PathVariable String uuid) {
        Path path = Paths.get(storeService.findByUUID(uuid).getFilepath());
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}


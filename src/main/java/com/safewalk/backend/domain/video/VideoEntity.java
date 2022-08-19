package com.safewalk.backend.domain.video;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity @Getter @ToString
@Table(name = "video")
public class VideoEntity {
    @Id
    private String fileUUID;
    private String filepath;
    private String originalFilename;
}


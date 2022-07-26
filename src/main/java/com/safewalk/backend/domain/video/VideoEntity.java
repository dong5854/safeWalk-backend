package com.safewalk.backend.domain.video;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Getter @ToString
@Table(name = "video")
public class VideoEntity {
    @Id
    private String filename;
    private String filepath;
}

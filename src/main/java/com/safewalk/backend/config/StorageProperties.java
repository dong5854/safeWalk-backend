package com.safewalk.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Getter @Setter
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "/filepath";
}

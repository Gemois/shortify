package com.gmoi.shortify.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "shortify.url")
public class UrlProperties {
    private int shortLength;
    private String hashSalt;
    private int shortMaxAttempts;
}
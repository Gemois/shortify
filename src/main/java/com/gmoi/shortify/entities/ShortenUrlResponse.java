package com.gmoi.shortify.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortenUrlResponse {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
}

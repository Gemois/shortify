package com.gmoi.shortify.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortenUrlCountResponse {
    private String originalUrl;
    private String shortUrl;
    private long clickCount;
    private LocalDateTime createdAt;
}

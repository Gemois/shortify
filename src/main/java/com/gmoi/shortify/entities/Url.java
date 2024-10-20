package com.gmoi.shortify.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Url {
    @Id
    private String shortUrl;
    private String originalUrl;
    private long clickCount;
    private LocalDateTime lastClickAt;
    private LocalDateTime expirationDate;
    @Version
    private long version;
    @CreatedDate
    private LocalDateTime createdAt;
}

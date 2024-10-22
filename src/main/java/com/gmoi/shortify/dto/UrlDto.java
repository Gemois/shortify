package com.gmoi.shortify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlDto {
    private String shortUrl;
    private String longUrl;
    private long clickCount;
    private String lastClickDate;
    private long inactiveDays;
    private String expirationDate;
    private String createdDate;
}

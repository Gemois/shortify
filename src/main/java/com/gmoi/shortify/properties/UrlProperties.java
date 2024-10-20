package com.gmoi.shortify.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "shortify.url")
public class UrlProperties {

    private ShortUrl shortUrl = new ShortUrl();
    private Scheduler scheduler = new Scheduler();

    @Data
    public static class ShortUrl {
        private int length;
        private String hashSalt;
        private int maxAttempts;
        private int expirationDays;
        private int inactiveDays;
    }

    @Data
    public static class Scheduler {
        private int threadPoolSize;
        private int cleanupRate;
    }
}
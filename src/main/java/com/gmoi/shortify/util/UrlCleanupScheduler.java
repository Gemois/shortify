package com.gmoi.shortify.util;

import com.gmoi.shortify.entities.Url;
import com.gmoi.shortify.properties.UrlProperties;
import com.gmoi.shortify.repositories.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlCleanupScheduler {

    private final UrlRepository urlRepository;
    private final UrlProperties urlProperties;

    @Scheduled(fixedRateString = "${shortify.url.scheduler.cleanup-rate}")
    public void cleanupUrls() {
        log.info("{} - Cleanup task started", Thread.currentThread().getName());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inactiveThreshold = now.minusDays(urlProperties.getShortUrl().getInactiveDays());

        List<Url> expiredUrls = urlRepository.findByExpirationDateBefore(now);
        List<Url> inactiveUrls = urlRepository.findByLastClickAtBefore(inactiveThreshold);

        expiredUrls.addAll(inactiveUrls);

        if (!expiredUrls.isEmpty()) {
            urlRepository.deleteAll(expiredUrls);
            log.info("Cleaned up {} URLs.", expiredUrls.size());
        }
    }
}

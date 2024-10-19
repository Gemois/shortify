package com.gmoi.shortify.util;

import com.gmoi.shortify.properties.UrlProperties;
import com.gmoi.shortify.repositories.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlIdentifierGenerator {

    private final UrlProperties urlProperties;
    private final UrlRepository urlRepository;

    private Hashids hashids;
    private Hashids getHashids() {
        if (hashids == null) {
            hashids = new Hashids(urlProperties.getHashSalt(), urlProperties.getShortLength());
        }
        return hashids;
    }

    public String generate() {
        int maxAttempts = urlProperties.getShortMaxAttempts();
        int attempt = 0;
        String shortUrl;

        do {
            shortUrl = getHashids().encode(System.nanoTime() + attempt);
            attempt++;
        } while (urlRepository.existsById(shortUrl) && attempt < maxAttempts);

        if (attempt == maxAttempts) {
            throw new IllegalStateException("Unable to generate unique short URL after " + maxAttempts + " attempts");
        }

        return shortUrl;
    }
}

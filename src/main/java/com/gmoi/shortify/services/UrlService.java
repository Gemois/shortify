package com.gmoi.shortify.services;

import com.gmoi.shortify.entities.ShortenUrlRequest;
import com.gmoi.shortify.entities.ShortenUrlResponse;
import com.gmoi.shortify.entities.Url;
import com.gmoi.shortify.repositories.UrlRepository;
import com.gmoi.shortify.util.UrlIdentifierGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlIdentifierGenerator urlIdentifierGenerator;

    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request) {

        if (request == null || !StringUtils.hasText(request.getOriginalUrl()))
            throw new IllegalStateException("Original url is missing");

        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(request.getOriginalUrl()))
            throw new IllegalStateException("Original url is invalid");

        Url url = Url.builder()
                .shortUrl(urlIdentifierGenerator.generate())
                .originalUrl(request.getOriginalUrl())
                .build();

        Url saved = urlRepository.insert(url);

        return ShortenUrlResponse.builder()
                .shortUrl(saved.getShortUrl())
                .originalUrl(saved.getOriginalUrl())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public String fetchOriginal(String shortUrl) {

        Url url = Optional.of(urlRepository.findById(shortUrl)).get().orElseThrow(
                () -> new RuntimeException("Could not find url with shortUrl: " + shortUrl)
        );

        return url.getOriginalUrl();
    }

}

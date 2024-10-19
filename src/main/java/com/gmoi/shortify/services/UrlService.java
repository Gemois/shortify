package com.gmoi.shortify.services;

import com.gmoi.shortify.entities.ShortenUrlRequest;
import com.gmoi.shortify.entities.ShortenUrlResponse;
import com.gmoi.shortify.entities.Url;
import com.gmoi.shortify.exceptions.UrlMalformedException;
import com.gmoi.shortify.exceptions.UrlMissingException;
import com.gmoi.shortify.exceptions.UrlNotFoundException;
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
            throw new UrlMissingException();

        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(request.getOriginalUrl()))
            throw new UrlMalformedException();

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
        Url url = Optional.of(urlRepository.findById(shortUrl)).get()
                .orElseThrow(UrlNotFoundException::new);

        return url.getOriginalUrl();
    }
}

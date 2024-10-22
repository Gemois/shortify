package com.gmoi.shortify.services;

import com.gmoi.shortify.dto.UrlDto;
import com.gmoi.shortify.entities.Url;
import com.gmoi.shortify.exceptions.UrlMalformedException;
import com.gmoi.shortify.exceptions.UrlMissingException;
import com.gmoi.shortify.exceptions.UrlNotFoundException;
import com.gmoi.shortify.properties.UrlProperties;
import com.gmoi.shortify.repositories.UrlRepository;
import com.gmoi.shortify.tools.UrlClickCounter;
import com.gmoi.shortify.tools.UrlIdentifierGenerator;
import com.gmoi.shortify.util.UrlUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlIdentifierGenerator urlIdentifierGenerator;
    private final UrlClickCounter urlClickCounter;
    private final UrlRepository urlRepository;
    private final UrlProperties urlProperties;

    public UrlDto shortenUrl(String longUrl) {

        if (!StringUtils.hasText(longUrl))
            throw new UrlMissingException();

        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(longUrl))
            throw new UrlMalformedException();

        LocalDateTime expirationDate = LocalDateTime.now().plusDays(urlProperties.getShortUrl().getExpirationDays());

        Url url = Url.builder()
                .shortUrl(urlIdentifierGenerator.generate())
                .longUrl(longUrl)
                .expirationDate(expirationDate)
                .build();

        Url saved = urlRepository.insert(url);

        return UrlDto.builder()
                .shortUrl(saved.getShortUrl())
                .longUrl(saved.getLongUrl())
                .build();
    }

    public String getLongUrl(String shortUrl) {

        Url url = Optional.of(urlRepository.findById(shortUrl)).get()
                .orElseThrow(UrlNotFoundException::new);

        urlClickCounter.increment(url);
        return url.getLongUrl();
    }

    public UrlDto getClickCount(String shortUrl) {

        Url url = Optional.of(urlRepository.findById(shortUrl)).get()
                .orElseThrow(UrlNotFoundException::new);

        return UrlDto.builder()
                .shortUrl(url.getShortUrl())
                .longUrl(url.getLongUrl())
                .clickCount(url.getClickCount())
                .lastClickDate(UrlUtil.formatDateTime(url.getLastClickDate()))
                .inactiveDays(UrlUtil.calcInactiveDays(url))
                .expirationDate(UrlUtil.formatDateTime(url.getExpirationDate()))
                .createdDate(UrlUtil.formatDateTime(url.getCreatedDate()))
                .build();
    }
}

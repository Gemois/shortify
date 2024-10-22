package com.gmoi.shortify.tools;

import com.gmoi.shortify.entities.Url;
import com.gmoi.shortify.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UrlClickCounter {

    private final UrlRepository urlRepository;

    public void increment(Url url) {
        url.setClickCount(url.getClickCount() + 1);
        url.setLastClickDate(LocalDateTime.now());
        urlRepository.save(url);
    }
}

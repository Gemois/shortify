package com.gmoi.shortify.controllers;

import com.gmoi.shortify.entities.ShortenUrlRequest;
import com.gmoi.shortify.entities.ShortenUrlResponse;
import com.gmoi.shortify.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequiredArgsConstructor
@Validated
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    private ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request) {
        return ResponseEntity.ok(urlService.shortenUrl(request));
    }

    @GetMapping("/{shortUrl}")
    private RedirectView redirect(@PathVariable String shortUrl) {
        return new RedirectView(urlService.fetchOriginal(shortUrl));
    }
}

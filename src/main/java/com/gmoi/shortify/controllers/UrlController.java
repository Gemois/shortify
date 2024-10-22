package com.gmoi.shortify.controllers;

import com.gmoi.shortify.dto.UrlDto;
import com.gmoi.shortify.services.UrlService;
import com.gmoi.shortify.util.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping({"/shorten", "/shorten/"})
    public String shortenUrl(@RequestParam String longUrl, Model model, HttpServletRequest httpServletRequest) {
        UrlDto urlDto = urlService.shortenUrl(longUrl);

        model.addAttribute("shortUrl", UrlUtil.constructFullShortUrl(httpServletRequest, urlDto.getShortUrl()));
        model.addAttribute("longUrl", urlDto.getLongUrl());
        model.addAttribute("shortUrlId", urlDto.getShortUrl());

        return "shorten";
    }

    @GetMapping("/{shortUrl}")
    private RedirectView redirect(@PathVariable String shortUrl) {
        return new RedirectView(urlService.getLongUrl(shortUrl));
    }

    @GetMapping({"/track", "/track/"})
    public String track() {
        return "track";
    }

    @GetMapping("/track/{shortUrl}")
    public String track(@PathVariable("shortUrl") String shortUrl, Model model, HttpServletRequest httpServletRequest) {
        UrlDto urlDto = urlService.getClickCount(shortUrl);

        model.addAttribute("shortUrl", UrlUtil.constructFullShortUrl(httpServletRequest, urlDto.getShortUrl()));
        model.addAttribute("longUrl", urlDto.getLongUrl());
        model.addAttribute("clickCount", urlDto.getClickCount());
        model.addAttribute("lastClickDate", urlDto.getLastClickDate());
        model.addAttribute("inactiveDays", urlDto.getInactiveDays());
        model.addAttribute("expirationDate", urlDto.getExpirationDate());
        model.addAttribute("createdDate", urlDto.getCreatedDate());

        return "track";
    }

    @GetMapping({"/about", "/about/"})
    public String about() {
        return "about";
    }
}

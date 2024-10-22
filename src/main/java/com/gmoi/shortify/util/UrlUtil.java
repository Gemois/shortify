package com.gmoi.shortify.util;

import com.gmoi.shortify.entities.Url;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UrlUtil {

    public static String constructFullShortUrl(HttpServletRequest request, String shortUrlId) {
        int serverPort = request.getServerPort();
        String portPart = (serverPort != 80 && serverPort != 443) ? ":" + serverPort : "";

        return request.getScheme() + "://" + request.getServerName() + portPart + "/" + shortUrlId;
    }

    public static long calcInactiveDays(Url url) {
        LocalDateTime from = url.getLastClickDate() != null ? url.getLastClickDate() : url.getCreatedDate();
        LocalDateTime to = LocalDateTime.now();
        return ChronoUnit.DAYS.between(from, to);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy h:mm a");
        return dateTime != null ? dateTime.format(formatter) : null;
    }
}

package com.gmoi.shortify.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super("Short URL not found.");
    }
}

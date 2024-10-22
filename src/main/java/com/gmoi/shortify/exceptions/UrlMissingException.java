package com.gmoi.shortify.exceptions;

public class UrlMissingException extends RuntimeException {
    public UrlMissingException() {
        super("Long URL is missing.");
    }
}

package com.gmoi.shortify.exceptions;

public class UrlMalformedException extends RuntimeException {
    public UrlMalformedException() {
        super("Original URL is malformed.");
    }
}

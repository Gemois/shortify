package com.gmoi.shortify.exceptions;

public class UrlMalformedException extends RuntimeException {
    public UrlMalformedException() {
        super("Long URL is invalid.");
    }
}

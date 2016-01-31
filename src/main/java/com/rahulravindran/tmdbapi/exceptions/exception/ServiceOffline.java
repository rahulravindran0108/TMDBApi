package com.rahulravindran.tmdbapi.exceptions.exception;


public class ServiceOffline extends TMDBApiError {
    public ServiceOffline(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

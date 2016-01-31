package com.rahulravindran.tmdbapi.exceptions.exception;


public class SuspendedApiKey extends TMDBApiError {
    public SuspendedApiKey(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

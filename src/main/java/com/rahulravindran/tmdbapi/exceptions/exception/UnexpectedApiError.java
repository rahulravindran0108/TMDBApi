package com.rahulravindran.tmdbapi.exceptions.exception;

public class UnexpectedApiError extends TMDBApiError {

    public UnexpectedApiError(int code, String message) {
        this(code, message, null, null);
    }

    public UnexpectedApiError(int code, String message, String id, String text) {
        super(code, message, id, text);
    }

}

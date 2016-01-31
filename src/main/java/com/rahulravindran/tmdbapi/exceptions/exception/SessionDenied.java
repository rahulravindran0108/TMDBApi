package com.rahulravindran.tmdbapi.exceptions.exception;

public class SessionDenied extends TMDBApiError {
    public SessionDenied(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

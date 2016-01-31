package com.rahulravindran.tmdbapi.exceptions.exception;

public class InvalidAcceptHeader extends TMDBApiError {
    public InvalidAcceptHeader(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

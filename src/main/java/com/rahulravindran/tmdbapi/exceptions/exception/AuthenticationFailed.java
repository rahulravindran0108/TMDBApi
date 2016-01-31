package com.rahulravindran.tmdbapi.exceptions.exception;

public class AuthenticationFailed extends TMDBApiError {
    public AuthenticationFailed(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

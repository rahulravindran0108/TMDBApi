package com.rahulravindran.tmdbapi.exceptions.exception;

public class TMDBInternalError extends TMDBApiError  {
    public TMDBInternalError(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

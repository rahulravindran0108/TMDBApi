package com.rahulravindran.tmdbapi.exceptions.exception;

/**
 * Created by rahulravindran on 31/01/16.
 */
public class ValidationFailed extends TMDBApiError {
    public ValidationFailed(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

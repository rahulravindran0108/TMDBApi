package com.rahulravindran.tmdbapi.exceptions.exception;

/**
 * Created by rahulravindran on 31/01/16.
 */
public class Failed extends TMDBApiError {
    public Failed(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

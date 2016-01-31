package com.rahulravindran.tmdbapi.exceptions.exception;

/**
 * Created by rahulravindran on 31/01/16.
 */
public class DuplicateEntry extends TMDBApiError {
    public DuplicateEntry(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

package com.rahulravindran.tmdbapi.exceptions.exception;

public class InvalidDateRange extends TMDBApiError {

    public InvalidDateRange(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }

}

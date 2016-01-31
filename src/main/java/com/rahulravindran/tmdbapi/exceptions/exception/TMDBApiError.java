package com.rahulravindran.tmdbapi.exceptions.exception;

import java.io.IOException;

/**
 * Created by rahulravindran on 31/01/16.
 */
public class TMDBApiError extends IOException {
    private int code;
    private String message;
    private String text;
    private String errorId;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }

    public String getErrorId() {
        return errorId;
    }

    public TMDBApiError(int code, String message, String errorId, String text) {
        this.code = code;
        this.message = message;
        this.errorId = errorId;
        this.text = text;
    }


}

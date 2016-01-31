package com.rahulravindran.tmdbapi.exceptions.exception;


public class DeviceDenied extends TMDBApiError {
    public DeviceDenied(int code, String message, String errorId, String text) {
        super(code, message, errorId, text);
    }
}

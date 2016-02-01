package com.rahulravindran.tmdbapi.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;
import java.io.IOException;
import com.rahulravindran.tmdbapi.exceptions.exception.AuthenticationFailed;
import com.rahulravindran.tmdbapi.exceptions.exception.DeviceDenied;
import com.rahulravindran.tmdbapi.exceptions.exception.DuplicateEntry;
import com.rahulravindran.tmdbapi.exceptions.exception.Failed;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidAcceptHeader;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidApiKey;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidDateRange;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidFormat;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidId;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidParameters;
import com.rahulravindran.tmdbapi.exceptions.exception.InvalidService;
import com.rahulravindran.tmdbapi.exceptions.exception.ServiceOffline;
import com.rahulravindran.tmdbapi.exceptions.exception.SessionDenied;
import com.rahulravindran.tmdbapi.exceptions.exception.SuspendedApiKey;
import com.rahulravindran.tmdbapi.exceptions.exception.TMDBApiError;
import com.rahulravindran.tmdbapi.exceptions.exception.TMDBInternalError;
import com.rahulravindran.tmdbapi.exceptions.exception.UnexpectedApiError;
import com.rahulravindran.tmdbapi.exceptions.exception.ValidationFailed;


public class ErrorHandlingInterceptor  implements Interceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            throw parseError(
                    response.code(),
                    response.message(),
                    response.body() != null ? response.body().string() : null
            );
        }
        return response;
    }

    private TMDBApiError parseError(int code, String message, String responseBody)
            throws IOException {
        JsonNode errorJsonNode = objectMapper.readTree(responseBody);
        int statusCode = errorJsonNode.path("status_code").asInt();
        String statusMessage = errorJsonNode.path("status_message").asText();
        System.out.println(statusCode);

        switch(statusCode) {
            case 2:
                return new InvalidService(code, message, new Integer(statusCode).toString(), statusMessage);
            case 3:
                return new AuthenticationFailed(code, message, new Integer(statusCode).toString(), statusMessage);
            case 4:
                return new InvalidFormat(code, message, new Integer(statusCode).toString(), statusMessage);
            case 5:
                return new InvalidParameters(code, message, new Integer(statusCode).toString(), statusMessage);
            case 6:
                return new InvalidId(code, message, new Integer(statusCode).toString(), statusMessage);
            case 7:
                return new InvalidApiKey(code, message, new Integer(statusCode).toString(), statusMessage);
            case 8:
                return new DuplicateEntry(code, message, new Integer(statusCode).toString(), statusMessage);
            case 9:
                return new ServiceOffline(code, message, new Integer(statusCode).toString(), statusMessage);
            case 10:
                return new SuspendedApiKey(code, message, new Integer(statusCode).toString(), statusMessage);
            case 11:
                return new TMDBInternalError(code, message, new Integer(statusCode).toString(), statusMessage);
            case 14:
                return new AuthenticationFailed(code, message, new Integer(statusCode).toString(), statusMessage);
            case 15:
                return new Failed(code, message, new Integer(statusCode).toString(), statusMessage);
            case 16:
                return new DeviceDenied(code, message, new Integer(statusCode).toString(), statusMessage);
            case 17:
                return new SessionDenied(code, message, new Integer(statusCode).toString(), statusMessage);
            case 18:
                return new ValidationFailed(code, message, new Integer(statusCode).toString(), statusMessage);
            case 19:
                return new InvalidAcceptHeader(code, message, new Integer(statusCode).toString(), statusMessage);
            case 20:
                return new InvalidDateRange(code, message, new Integer(statusCode).toString(), statusMessage);
            default:
                return new UnexpectedApiError(code, message);

        }

    }
}

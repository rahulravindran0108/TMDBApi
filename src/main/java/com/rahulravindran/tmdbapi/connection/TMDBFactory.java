package com.rahulravindran.tmdbapi.connection;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

import com.rahulravindran.tmdbapi.exceptions.ErrorHandlingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class TMDBFactory {

    public static final String TMDB_API_URL = "http://api.themoviedb.org/3/";
    private OkHttpClient httpClient;

    //sets the okhttp client to make all request using api key
    public TMDBFactory(final String api_key) {
        this.httpClient = new OkHttpClient();
        this.httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.httpUrl().
                        newBuilder().addQueryParameter("api_key",api_key).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });
        this.httpClient.interceptors().add(new ErrorHandlingInterceptor());
    }

    public TMDBApi createAPI() {
        Retrofit retrofit =  new Retrofit.Builder()
                                .baseUrl(TMDB_API_URL)
                                .client(httpClient)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .build();
        return retrofit.create(TMDBApi.class);
    }

    public String getAPIBaseUrl() {
        return TMDB_API_URL;
    }
}

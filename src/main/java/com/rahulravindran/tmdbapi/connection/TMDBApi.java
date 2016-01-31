package com.rahulravindran.tmdbapi.connection;

import com.rahulravindran.tmdbapi.entitites.Genres;
import com.rahulravindran.tmdbapi.entitites.MovieDB;
import com.rahulravindran.tmdbapi.entitites.MovieReview;
import com.rahulravindran.tmdbapi.entitites.Sort;
import com.rahulravindran.tmdbapi.entitites.Video;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface TMDBApi {
    //get movies data
    @GET("discover/movie")
    Observable<MovieDB.Response> discoverMovies(
            @Query("sort_by") Sort sort,
            @Query("page") int page);

    //get movies data with adult
    @GET("discover/movie")
    Observable<MovieDB.Response> discoverMovies(
            @Query("sort_by") Sort sort,
            @Query("page") int page,
            @Query("include_adult") boolean includeAdult);

    //reviews get api
    @GET("movie/{id}/reviews") Observable<MovieReview.Response> getReview(
            @Path("id") long id
    );

    //genres call
    @GET("genre/movie/list")
    Observable<Genres.Response> getGenres(
    );

    //get trailers
    @GET("movie/{id}/videos") Observable<Video.Response> videos(
            @Path("id") long movieId
    );
}

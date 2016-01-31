package example.rahul_ravindran.com.tmdbapi.connection;

import example.rahul_ravindran.com.tmdbapi.entitites.Genres;
import example.rahul_ravindran.com.tmdbapi.entitites.MovieDB;
import example.rahul_ravindran.com.tmdbapi.entitites.MovieReview;
import example.rahul_ravindran.com.tmdbapi.entitites.Sort;
import example.rahul_ravindran.com.tmdbapi.entitites.Video;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface TMDBApi {
    //get movies data
    @GET("discover/movie")
    Observable<MovieDB.Response> discoverMovies(
            @Query("api_key") String api_key,
            @Query("sort_by") Sort sort,
            @Query("page") int page);

    //get movies data with adult
    @GET("discover/movie")
    Observable<MovieDB.Response> discoverMovies(
            @Query("api_key") String api_key,
            @Query("sort_by") Sort sort,
            @Query("page") int page,
            @Query("include_adult") boolean includeAdult);

    //reviews get api
    @GET("movie/{id}/reviews") Observable<MovieReview.Response> getReview(
            @Path("id") long id,
            @Query("api_key") String api_key
    );

    //genres call
    @GET("genre/movie/list")
    Observable<Genres.Response> getGenres(
            @Query("api_key") String api_key
    );

    //get trailers
    @GET("movie/{id}/videos") Observable<Video.Response> videos(
            @Path("id") long movieId,
            @Query("api_key") String api_key);
}

package com.rahulravindran.tmdbapi.connection;


import com.squareup.okhttp.Dispatcher;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import com.rahulravindran.tmdbapi.entitites.Genres;
import com.rahulravindran.tmdbapi.entitites.MovieDB;
import com.rahulravindran.tmdbapi.entitites.Sort;
import junitx.util.PrivateAccessor;
import rx.Observable;
import rx.functions.Action1;


public class TMDBApiTest {
    private TMDBApi tmdbAPI;
    private List<MovieDB> movies;
    private List<Genres> genres;
    private static String API_KEY = "fff1e40cbc36f1d068ac939ea21bf343";


    @Before
    public void setup() throws IOException {

        TMDBFactory tmdbFactory = new TMDBFactory(API_KEY);

        Dispatcher synchronousDispatcher = new Dispatcher(newSynchronousExecutorService());

        try {
            OkHttpClient httpClient = (OkHttpClient) PrivateAccessor.getField(tmdbFactory, "httpClient");
            httpClient.setDispatcher(synchronousDispatcher);
        } catch (NoSuchFieldException e) {
            Assert.fail(e.toString());
        }

        tmdbAPI = tmdbFactory.createAPI();


    }



    //tests discover movies
    @Test
    public void testDiscoverMovies() {

        Observable<MovieDB.Response> movies = tmdbAPI.discoverMovies(Sort.popularity, 1)
                .subscribeOn(Schedulers.immediate());

        movies.subscribe(new Action1<MovieDB.Response>() {
            @Override
            public void call(MovieDB.Response response) {
                onMoviesLoaded(response.movies);
            }
        });

        assert this.movies.size() > 0;

    }

    @Test
    public void getGenres() {
        Observable<Genres.Response> genres =
                tmdbAPI.getGenres().subscribeOn(Schedulers.immediate());

        genres.subscribe(new Action1<Genres.Response>() {
            @Override
            public void call(Genres.Response response) {
                onGenresLoaded(response.genres);
            }
        });

        assert this.genres.size() > 0;
    }

    private void onMoviesLoaded(List<MovieDB> movieDBs) {
        this.movies = movieDBs;
    }

    private void onGenresLoaded(List<Genres> genres) {
        this.genres = genres;
    }

    public static ExecutorService newSynchronousExecutorService() {
        return new AbstractExecutorService() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }

            @Override
            public void shutdown() {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<Runnable> shutdownNow() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isShutdown() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isTerminated() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                throw new UnsupportedOperationException();
            }
        };
    }

}

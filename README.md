# TMDBApi

The movies DB API Android Library Module. This repository contains an Android Module for ingesting data from TheMoviesDB. This module was part of the Popular Movie Application that I had made for Android Nanodegree Program.

This library requires Android 2.3 and uses java 7

## Installation

Using Gradle:
```
repositories {
        jcenter()
}

dependencies {
    ...
    compile 'com.rahulravindran:tmdbapi:0.1.1'
}
```

## Common issues

One of the common issues is duplication of Licence/Notice file due to one of the dependencies. Here is a quick fix for it:

```
android {
    ...
    packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/NOTICE'
    }
}
```

## Usage

### Basic usage

The TMDBApi is provided within the library and this is used to query the various endpoints. As of now, only a limited number of urls are available to query. These urls are just enough for the Android Nanodegree Program.
All you need to make use of this library is your TMDB api key and your good to go.

```java
TMDBFactory tmdbFactory = new TMDBFactory(API_KEY);
TMDBApi tmdbAPI = tmdbFactory.createAPI();
```

The above two lines of code is enough to create the api. Using the tmdbApi you can now query the various urls that is available in the API.

### Discover Movies

Here is a small snipped that lets you query to the discover API

```java
Observable<MovieDB.Response> movies = tmdbAPI.discoverMovies(Sort.popularity, 1)
                .subscribeOn(Schedulers.io());

movies.subscribe(new Action1<MovieDB.Response>() {
    @Override
    public void call(MovieDB.Response response) {
        onMoviesLoaded(response.movies); //response.movies is a List<MovieDB>
    }
});

```

### Genres

The api also gives call to the genres endpoint to get a list of all the genres.

```java
Observable<Genres.Response> genres =
                    tmdbAPI.getGenres().subscribeOn(Schedulers.io());

genres.subscribe(new Action1<Genres.Response>() {
    @Override
    public void call(Genres.Response response) {
                onGenresLoaded(response.genres); // this is a List<Genre>
    }
});
```

### Video   

This api also gives call to the video endpoint in order to get the video of a specific movie

```java
Observable<Video.Response> videos =
                    tmdbAPI.videos().subscribeOn(Schedulers.io());

videos.subscribe(new Action1<Video.Response>() {
    @Override
    public void call(Video.Response response) {
                onVideosLoaded(response.videos); // this is a List<Video>
    }
});
```

### Sort

This api gives the ability to sort based on following categories

- popularity("popularity.desc"),
- vote_average("vote_average.desc"),
- vote_count("vote_count.desc"),
- original_title("original_title.desc"),
- primary_release_date("primary_release_date.desc"),
- revenue("revenue.desc"),
- release_date("release_date");

The Sort class is particularly useful when querying the discover movies endpoint. 
This gives the ability to sort on various parameters.

### Reviews

This library also supports call to the review endpoint for getting specific movies review

### Synchronous Call

In order to create a synchronous call, you can exeucte the following snippet of code

```java
Dispatcher synchronousDispatcher = new Dispatcher(newSynchronousExecutorService());

try {
    OkHttpClient httpClient = (OkHttpClient) PrivateAccessor.getField(tmdbFactory, "httpClient");
    httpClient.setDispatcher(synchronousDispatcher);
} catch (NoSuchFieldException e) {
     Assert.fail(e.toString());
}

tmdbAPI = tmdbFactory.createAPI();
``` 

This needs to be done in the initial creating of the object. All subsequent calls to the api wil now be synchronous


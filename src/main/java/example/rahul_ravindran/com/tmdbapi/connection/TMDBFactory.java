package example.rahul_ravindran.com.tmdbapi.connection;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by rahulravindran on 30/01/16.
 */
public class TMDBFactory {

    public static final String TMDB_API_URL = "http://api.themoviedb.org/3/";
    private OkHttpClient httpClient;

    public TMDBFactory(String api_key) {
        this.httpClient = new OkHttpClient();
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

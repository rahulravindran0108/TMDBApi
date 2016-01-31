package example.rahul_ravindran.com.tmdbapi.entitites;

import java.io.Serializable;

/**
 * Created by rahulravindran on 30/01/16.
 */
public enum Sort implements Serializable {

    popularity("popularity.desc"),
    vote_average("vote_average.desc"),
    vote_count("vote_count.desc"),
    original_title("original_title.desc"),
    primary_release_date("primary_release_date.desc"),
    revenue("revenue.desc"),
    release_date("release_date");


    private String value;

    Sort(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }

    public static Sort fromString(String value) {
        if(value!=null) {
            for(Sort sort: Sort.values()) {
                if(value.equalsIgnoreCase(sort.value))
                    return sort;
            }
        }
        throw new NullPointerException("No value found containing "+value);
    }
}

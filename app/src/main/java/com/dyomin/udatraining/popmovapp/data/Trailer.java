package com.dyomin.udatraining.popmovapp.data;

/**
 * Contains trailer information.
 * Created by Admin on 02.02.2016.
 */
public class Trailer {

    private String name;
    private String trailerKey;
    private int movieTmdbId;

    public Trailer(String name, String trailerKey, int movieTmdbId) {
        this.name = name;
        this.trailerKey = trailerKey;
        this.movieTmdbId = movieTmdbId;
    }

    public int getMovieTmdbId() {
        return movieTmdbId;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public String getName() {
        return name;
    }
}

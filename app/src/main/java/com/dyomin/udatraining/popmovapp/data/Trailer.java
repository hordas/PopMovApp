package com.dyomin.udatraining.popmovapp.data;

/**
 * Contains trailer information.
 * Created by Admin on 02.02.2016.
 */
public class Trailer {

    private String name;
    private String trailerKey;
    private int movieTmdbId;

    public int getMovieTmdbId() {
        return movieTmdbId;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public void setMovieTmdbId(int movieTmdbId) {
        this.movieTmdbId = movieTmdbId;
    }
}

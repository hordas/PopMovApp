package com.dyomin.udatraining.popmovapp.data.trailer;

/**
 * Contains values for trailer entity.
 * --
 * Created by Admin on 02.02.2016.
 */
public class Trailer {

    private String name;
    private String trailerKey;
    private int movieTmdbId;

    @SuppressWarnings("unused")
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

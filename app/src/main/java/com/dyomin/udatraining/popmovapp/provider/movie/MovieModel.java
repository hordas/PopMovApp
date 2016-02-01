package com.dyomin.udatraining.popmovapp.provider.movie;

import com.dyomin.udatraining.popmovapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Movie details
 */
public interface MovieModel extends BaseModel {

    /**
     * Movie id at TMDB
     */
    int getTmdbId();

    /**
     * Movie title
     * Cannot be {@code null}.
     */
    @NonNull
    String getOriginalTitle();

    /**
     * Movie poster URI
     * Can be {@code null}.
     */
    @Nullable
    String getMoviePosterUri();

    /**
     * Movie release date
     * Can be {@code null}.
     */
    @Nullable
    String getMovieReleaseDate();

    /**
     * Vote average
     * Can be {@code null}.
     */
    @Nullable
    Float getVoteAverage();

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();
}

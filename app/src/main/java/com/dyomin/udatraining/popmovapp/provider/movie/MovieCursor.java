package com.dyomin.udatraining.popmovapp.provider.movie;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie id at TMDB
     */
    public int getTmdbId() {
        Integer res = getIntegerOrNull(MovieColumns.TMDB_ID);
        if (res == null)
            throw new NullPointerException("The value of 'tmdb_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie title
     * Cannot be {@code null}.
     */
    @NonNull
    public String getOriginalTitle() {
        String res = getStringOrNull(MovieColumns.ORIGINAL_TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'original_title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie poster URI
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePosterUri() {
        String res = getStringOrNull(MovieColumns.MOVIE_POSTER_URI);
        return res;
    }

    /**
     * Movie release date
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieReleaseDate() {
        String res = getStringOrNull(MovieColumns.MOVIE_RELEASE_DATE);
        return res;
    }

    /**
     * Vote average
     * Can be {@code null}.
     */
    @Nullable
    public Float getVoteAverage() {
        Float res = getFloatOrNull(MovieColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }
}

package com.dyomin.udatraining.popmovapp.provider.video;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractCursor;
import com.dyomin.udatraining.popmovapp.provider.movie.*;

/**
 * Cursor wrapper for the {@code video} table.
 */
public class VideoCursor extends AbstractCursor implements VideoModel {
    public VideoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(VideoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(VideoColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code trailer_key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getTrailerKey() {
        String res = getStringOrNull(VideoColumns.TRAILER_KEY);
        if (res == null)
            throw new NullPointerException("The value of 'trailer_key' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public int getMovieId() {
        Integer res = getIntegerOrNull(VideoColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie id at TMDB
     */
    public int getMovieTmdbId() {
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
    public String getMovieOriginalTitle() {
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
    public String getMovieMoviePosterUri() {
        String res = getStringOrNull(MovieColumns.MOVIE_POSTER_URI);
        return res;
    }

    /**
     * Movie release date
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieMovieReleaseDate() {
        String res = getStringOrNull(MovieColumns.MOVIE_RELEASE_DATE);
        return res;
    }

    /**
     * Vote average
     * Can be {@code null}.
     */
    @Nullable
    public Float getMovieVoteAverage() {
        Float res = getFloatOrNull(MovieColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }
}

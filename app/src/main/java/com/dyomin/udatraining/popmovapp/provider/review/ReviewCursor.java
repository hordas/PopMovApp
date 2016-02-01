package com.dyomin.udatraining.popmovapp.provider.review;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractCursor;
import com.dyomin.udatraining.popmovapp.provider.movie.*;

/**
 * Cursor wrapper for the {@code review} table.
 */
public class ReviewCursor extends AbstractCursor implements ReviewModel {
    public ReviewCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ReviewColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAuthor() {
        String res = getStringOrNull(ReviewColumns.AUTHOR);
        if (res == null)
            throw new NullPointerException("The value of 'author' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getContent() {
        String res = getStringOrNull(ReviewColumns.CONTENT);
        if (res == null)
            throw new NullPointerException("The value of 'content' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public int getMovieId() {
        Integer res = getIntegerOrNull(ReviewColumns.MOVIE_ID);
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

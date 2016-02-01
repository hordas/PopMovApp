package com.dyomin.udatraining.popmovapp.provider.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Movie id at TMDB
     */
    public MovieContentValues putTmdbId(int value) {
        mContentValues.put(MovieColumns.TMDB_ID, value);
        return this;
    }


    /**
     * Movie title
     */
    public MovieContentValues putOriginalTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("originalTitle must not be null");
        mContentValues.put(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }


    /**
     * Movie poster URI
     */
    public MovieContentValues putMoviePosterUri(@Nullable String value) {
        mContentValues.put(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieContentValues putMoviePosterUriNull() {
        mContentValues.putNull(MovieColumns.MOVIE_POSTER_URI);
        return this;
    }

    /**
     * Movie release date
     */
    public MovieContentValues putMovieReleaseDate(@Nullable String value) {
        mContentValues.put(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieContentValues putMovieReleaseDateNull() {
        mContentValues.putNull(MovieColumns.MOVIE_RELEASE_DATE);
        return this;
    }

    /**
     * Vote average
     */
    public MovieContentValues putVoteAverage(@Nullable Float value) {
        mContentValues.put(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieContentValues putVoteAverageNull() {
        mContentValues.putNull(MovieColumns.VOTE_AVERAGE);
        return this;
    }

    public MovieContentValues putOverview(@Nullable String value) {
        mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieContentValues putOverviewNull() {
        mContentValues.putNull(MovieColumns.OVERVIEW);
        return this;
    }
}

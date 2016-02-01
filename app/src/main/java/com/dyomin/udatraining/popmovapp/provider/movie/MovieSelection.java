package com.dyomin.udatraining.popmovapp.provider.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractSelection;

/**
 * Selection for the {@code movie} table.
 */
public class MovieSelection extends AbstractSelection<MovieSelection> {
    @Override
    protected Uri baseUri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieCursor query(Context context) {
        return query(context, null);
    }


    public MovieSelection id(long... value) {
        addEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection idNot(long... value) {
        addNotEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection orderById(boolean desc) {
        orderBy("movie." + MovieColumns._ID, desc);
        return this;
    }

    public MovieSelection orderById() {
        return orderById(false);
    }

    public MovieSelection tmdbId(int... value) {
        addEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection tmdbIdNot(int... value) {
        addNotEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection tmdbIdGt(int value) {
        addGreaterThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public MovieSelection tmdbIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public MovieSelection tmdbIdLt(int value) {
        addLessThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public MovieSelection tmdbIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public MovieSelection orderByTmdbId(boolean desc) {
        orderBy(MovieColumns.TMDB_ID, desc);
        return this;
    }

    public MovieSelection orderByTmdbId() {
        orderBy(MovieColumns.TMDB_ID, false);
        return this;
    }

    public MovieSelection originalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection originalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection originalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection originalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection originalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection originalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieSelection orderByOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public MovieSelection orderByOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public MovieSelection moviePosterUri(String... value) {
        addEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection moviePosterUriNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection moviePosterUriLike(String... value) {
        addLike(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection moviePosterUriContains(String... value) {
        addContains(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection moviePosterUriStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection moviePosterUriEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public MovieSelection orderByMoviePosterUri(boolean desc) {
        orderBy(MovieColumns.MOVIE_POSTER_URI, desc);
        return this;
    }

    public MovieSelection orderByMoviePosterUri() {
        orderBy(MovieColumns.MOVIE_POSTER_URI, false);
        return this;
    }

    public MovieSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public MovieSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, desc);
        return this;
    }

    public MovieSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, false);
        return this;
    }

    public MovieSelection voteAverage(Float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageNot(Float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection orderByVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieSelection orderByVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public MovieSelection overview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection orderByOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public MovieSelection orderByOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }
}

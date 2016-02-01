package com.dyomin.udatraining.popmovapp.provider.review;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractSelection;
import com.dyomin.udatraining.popmovapp.provider.movie.*;

/**
 * Selection for the {@code review} table.
 */
public class ReviewSelection extends AbstractSelection<ReviewSelection> {
    @Override
    protected Uri baseUri() {
        return ReviewColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ReviewCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ReviewCursor query(Context context) {
        return query(context, null);
    }


    public ReviewSelection id(long... value) {
        addEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection idNot(long... value) {
        addNotEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection orderById(boolean desc) {
        orderBy("review." + ReviewColumns._ID, desc);
        return this;
    }

    public ReviewSelection orderById() {
        return orderById(false);
    }

    public ReviewSelection author(String... value) {
        addEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorNot(String... value) {
        addNotEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorLike(String... value) {
        addLike(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorContains(String... value) {
        addContains(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorStartsWith(String... value) {
        addStartsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorEndsWith(String... value) {
        addEndsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection orderByAuthor(boolean desc) {
        orderBy(ReviewColumns.AUTHOR, desc);
        return this;
    }

    public ReviewSelection orderByAuthor() {
        orderBy(ReviewColumns.AUTHOR, false);
        return this;
    }

    public ReviewSelection content(String... value) {
        addEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentNot(String... value) {
        addNotEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentLike(String... value) {
        addLike(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentContains(String... value) {
        addContains(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentStartsWith(String... value) {
        addStartsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentEndsWith(String... value) {
        addEndsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection orderByContent(boolean desc) {
        orderBy(ReviewColumns.CONTENT, desc);
        return this;
    }

    public ReviewSelection orderByContent() {
        orderBy(ReviewColumns.CONTENT, false);
        return this;
    }

    public ReviewSelection movieId(int... value) {
        addEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdNot(int... value) {
        addNotEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdGt(int value) {
        addGreaterThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLt(int value) {
        addLessThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLtEq(int value) {
        addLessThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieId(boolean desc) {
        orderBy(ReviewColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieId() {
        orderBy(ReviewColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieTmdbId(int... value) {
        addEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieTmdbIdNot(int... value) {
        addNotEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieTmdbIdGt(int value) {
        addGreaterThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public ReviewSelection movieTmdbIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public ReviewSelection movieTmdbIdLt(int value) {
        addLessThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public ReviewSelection movieTmdbIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieTmdbId(boolean desc) {
        orderBy(MovieColumns.TMDB_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieTmdbId() {
        orderBy(MovieColumns.TMDB_ID, false);
        return this;
    }

    public ReviewSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public ReviewSelection movieMoviePosterUri(String... value) {
        addEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection movieMoviePosterUriNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection movieMoviePosterUriLike(String... value) {
        addLike(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection movieMoviePosterUriContains(String... value) {
        addContains(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection movieMoviePosterUriStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection movieMoviePosterUriEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public ReviewSelection orderByMovieMoviePosterUri(boolean desc) {
        orderBy(MovieColumns.MOVIE_POSTER_URI, desc);
        return this;
    }

    public ReviewSelection orderByMovieMoviePosterUri() {
        orderBy(MovieColumns.MOVIE_POSTER_URI, false);
        return this;
    }

    public ReviewSelection movieMovieReleaseDate(String... value) {
        addEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieMovieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieMovieReleaseDateLike(String... value) {
        addLike(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieMovieReleaseDateContains(String... value) {
        addContains(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieMovieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieMovieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection orderByMovieMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, desc);
        return this;
    }

    public ReviewSelection orderByMovieMovieReleaseDate() {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, false);
        return this;
    }

    public ReviewSelection movieVoteAverage(Float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageNot(Float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public ReviewSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public ReviewSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }
}

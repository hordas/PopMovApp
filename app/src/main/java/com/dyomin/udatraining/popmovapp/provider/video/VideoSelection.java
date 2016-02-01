package com.dyomin.udatraining.popmovapp.provider.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractSelection;
import com.dyomin.udatraining.popmovapp.provider.movie.*;

/**
 * Selection for the {@code video} table.
 */
public class VideoSelection extends AbstractSelection<VideoSelection> {
    @Override
    protected Uri baseUri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VideoCursor} object, which is positioned before the first entry, or null.
     */
    public VideoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VideoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public VideoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VideoCursor} object, which is positioned before the first entry, or null.
     */
    public VideoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VideoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public VideoCursor query(Context context) {
        return query(context, null);
    }


    public VideoSelection id(long... value) {
        addEquals("video." + VideoColumns._ID, toObjectArray(value));
        return this;
    }

    public VideoSelection idNot(long... value) {
        addNotEquals("video." + VideoColumns._ID, toObjectArray(value));
        return this;
    }

    public VideoSelection orderById(boolean desc) {
        orderBy("video." + VideoColumns._ID, desc);
        return this;
    }

    public VideoSelection orderById() {
        return orderById(false);
    }

    public VideoSelection name(String... value) {
        addEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameNot(String... value) {
        addNotEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameLike(String... value) {
        addLike(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameContains(String... value) {
        addContains(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameStartsWith(String... value) {
        addStartsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameEndsWith(String... value) {
        addEndsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection orderByName(boolean desc) {
        orderBy(VideoColumns.NAME, desc);
        return this;
    }

    public VideoSelection orderByName() {
        orderBy(VideoColumns.NAME, false);
        return this;
    }

    public VideoSelection trailerKey(String... value) {
        addEquals(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection trailerKeyNot(String... value) {
        addNotEquals(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection trailerKeyLike(String... value) {
        addLike(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection trailerKeyContains(String... value) {
        addContains(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection trailerKeyStartsWith(String... value) {
        addStartsWith(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection trailerKeyEndsWith(String... value) {
        addEndsWith(VideoColumns.TRAILER_KEY, value);
        return this;
    }

    public VideoSelection orderByTrailerKey(boolean desc) {
        orderBy(VideoColumns.TRAILER_KEY, desc);
        return this;
    }

    public VideoSelection orderByTrailerKey() {
        orderBy(VideoColumns.TRAILER_KEY, false);
        return this;
    }

    public VideoSelection movieId(int... value) {
        addEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdNot(int... value) {
        addNotEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdGt(int value) {
        addGreaterThan(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdLt(int value) {
        addLessThan(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdLtEq(int value) {
        addLessThanOrEquals(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection orderByMovieId(boolean desc) {
        orderBy(VideoColumns.MOVIE_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieId() {
        orderBy(VideoColumns.MOVIE_ID, false);
        return this;
    }

    public VideoSelection movieTmdbId(int... value) {
        addEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieTmdbIdNot(int... value) {
        addNotEquals(MovieColumns.TMDB_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieTmdbIdGt(int value) {
        addGreaterThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public VideoSelection movieTmdbIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public VideoSelection movieTmdbIdLt(int value) {
        addLessThan(MovieColumns.TMDB_ID, value);
        return this;
    }

    public VideoSelection movieTmdbIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.TMDB_ID, value);
        return this;
    }

    public VideoSelection orderByMovieTmdbId(boolean desc) {
        orderBy(MovieColumns.TMDB_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieTmdbId() {
        orderBy(MovieColumns.TMDB_ID, false);
        return this;
    }

    public VideoSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public VideoSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public VideoSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public VideoSelection movieMoviePosterUri(String... value) {
        addEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection movieMoviePosterUriNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection movieMoviePosterUriLike(String... value) {
        addLike(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection movieMoviePosterUriContains(String... value) {
        addContains(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection movieMoviePosterUriStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection movieMoviePosterUriEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_POSTER_URI, value);
        return this;
    }

    public VideoSelection orderByMovieMoviePosterUri(boolean desc) {
        orderBy(MovieColumns.MOVIE_POSTER_URI, desc);
        return this;
    }

    public VideoSelection orderByMovieMoviePosterUri() {
        orderBy(MovieColumns.MOVIE_POSTER_URI, false);
        return this;
    }

    public VideoSelection movieMovieReleaseDate(String... value) {
        addEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieMovieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieMovieReleaseDateLike(String... value) {
        addLike(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieMovieReleaseDateContains(String... value) {
        addContains(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieMovieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieMovieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public VideoSelection orderByMovieMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, desc);
        return this;
    }

    public VideoSelection orderByMovieMovieReleaseDate() {
        orderBy(MovieColumns.MOVIE_RELEASE_DATE, false);
        return this;
    }

    public VideoSelection movieVoteAverage(Float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageNot(Float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public VideoSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public VideoSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public VideoSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }
}

package com.dyomin.udatraining.popmovapp.provider.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import com.dyomin.udatraining.popmovapp.provider.PMAProvider;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieColumns;
import com.dyomin.udatraining.popmovapp.provider.review.ReviewColumns;
import com.dyomin.udatraining.popmovapp.provider.video.VideoColumns;

/**
 * Movie details
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(PMAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Movie id at TMDB
     */
    public static final String TMDB_ID = "tmdb_id";

    /**
     * Movie title
     */
    public static final String ORIGINAL_TITLE = "original_title";

    /**
     * Movie poster URI
     */
    public static final String MOVIE_POSTER_URI = "movie_poster_uri";

    /**
     * Movie release date
     */
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";

    /**
     * Vote average
     */
    public static final String VOTE_AVERAGE = "vote_average";

    public static final String OVERVIEW = "overview";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TMDB_ID,
            ORIGINAL_TITLE,
            MOVIE_POSTER_URI,
            MOVIE_RELEASE_DATE,
            VOTE_AVERAGE,
            OVERVIEW
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TMDB_ID) || c.contains("." + TMDB_ID)) return true;
            if (c.equals(ORIGINAL_TITLE) || c.contains("." + ORIGINAL_TITLE)) return true;
            if (c.equals(MOVIE_POSTER_URI) || c.contains("." + MOVIE_POSTER_URI)) return true;
            if (c.equals(MOVIE_RELEASE_DATE) || c.contains("." + MOVIE_RELEASE_DATE)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
        }
        return false;
    }

}

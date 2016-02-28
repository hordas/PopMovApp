package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.os.Bundle;

import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;

/**
 * Contains methods for storing into and reading movie data from intents and bundles.
 * --
 * Created by Admin on 29.02.2016.
 */
public class FragmentInterconnectionHelper {

    public static final String MOVIE_TMDB_ID = "movie_tmdb_id";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_OVERVIEW = "movie_overview";
    public static final String MOVIE_VOTE_AVERAGE = "movie_vote_average";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_POSTER_URL = "movie_poster_url";

    public static final int NOT_PRESENT = -1;

    public static Bundle putDataIntoTheBundle(MovieDetails movieDetails) {
        Bundle args = new Bundle();
        args.putInt(MOVIE_TMDB_ID, movieDetails.getMovieId());
        args.putString(MOVIE_TITLE, movieDetails.getTitle());
        args.putString(MOVIE_OVERVIEW, movieDetails.getOverview());
        args.putString(MOVIE_VOTE_AVERAGE, movieDetails.getVoteAverage());
        args.putString(MOVIE_RELEASE_DATE, movieDetails.getReleaseDate());
        args.putString(MOVIE_POSTER_URL, movieDetails.getPosterUrl());
        return args;
    }

    public static Bundle createBundleFromDetailsIntent(Intent intent) {
        return putDataIntoTheBundle(createPosterFromIntentData(intent));
    }

    public static MovieDetails createPosterFromIntentData(Intent intent) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setMovieId(intent.getIntExtra(MOVIE_TMDB_ID, -1));
        movieDetails.setTitle(intent.getStringExtra(MOVIE_TITLE));
        movieDetails.setOverview(intent.getStringExtra(MOVIE_OVERVIEW));
        movieDetails.setVoteAverage(intent.getStringExtra(MOVIE_VOTE_AVERAGE));
        movieDetails.setReleaseDate(intent.getStringExtra(MOVIE_RELEASE_DATE));
        movieDetails.setPosterUrl(intent.getStringExtra(MOVIE_POSTER_URL));
        return movieDetails;
    }

    public static Intent puDataIntoIntent(Intent intent, MovieDetails movieDetails) {
        intent.putExtra(MOVIE_TMDB_ID, movieDetails.getMovieId());
        intent.putExtra(MOVIE_TITLE, movieDetails.getTitle());
        intent.putExtra(MOVIE_OVERVIEW, movieDetails.getOverview());
        intent.putExtra(MOVIE_VOTE_AVERAGE, movieDetails.getVoteAverage());
        intent.putExtra(MOVIE_RELEASE_DATE, movieDetails.getReleaseDate());
        intent.putExtra(MOVIE_POSTER_URL, movieDetails.getPosterUrl());
        return intent;
    }

    public static MovieDetails getMovieDataFromTheBundle(Bundle args) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setMovieId(args.getInt(MOVIE_TMDB_ID));
        movieDetails.setTitle(args.getString(MOVIE_TITLE));
        movieDetails.setOverview(args.getString(MOVIE_OVERVIEW));
        movieDetails.setVoteAverage(args.getString(MOVIE_VOTE_AVERAGE));
        movieDetails.setReleaseDate(args.getString(MOVIE_RELEASE_DATE));
        movieDetails.setPosterUrl(args.getString(MOVIE_POSTER_URL));
        return movieDetails;
    }
}

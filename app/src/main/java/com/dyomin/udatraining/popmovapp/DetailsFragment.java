package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.squareup.picasso.Picasso;


/**
 * Contains information about selected movie.
 */
public class DetailsFragment extends Fragment {

    public static final String MOVIE_TMDB_ID = "movie_tmdb_id";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_OVERVIEW = "movie_overview";
    public static final String MOVIE_VOTE_AVERAGE = "movie_vote_average";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_POSTER_URL = "movie_poster_url";

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private int movieId;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ImageView posterView = (ImageView) v.findViewById(R.id.imageview_movie_poster);
        TextView title = (TextView) v.findViewById(R.id.textview_original_title);
        TextView overview = (TextView) v.findViewById(R.id.textview_movie_synopsis);
        TextView voteAverage = (TextView) v.findViewById(R.id.textview_movie_rating);
        TextView releaseDate = (TextView) v.findViewById(R.id.textview_movie_release_date);

        Bundle args = getArguments();
        movieId = args.getInt(MOVIE_TMDB_ID);

        Picasso.with(getActivity()).load(
                        Connection.getImageUrl(args.getString(MOVIE_POSTER_URL))
                ).into(posterView);
                title.setText(args.getString(MOVIE_TITLE));
                overview.setText(args.getString(MOVIE_OVERVIEW));
                voteAverage.setText(args.getString(MOVIE_VOTE_AVERAGE));
                releaseDate.setText(args.getString(MOVIE_RELEASE_DATE));

        return v;
    }

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
        intent.putExtra(DetailsFragment.MOVIE_TMDB_ID, movieDetails.getMovieId());
        intent.putExtra(DetailsFragment.MOVIE_TITLE, movieDetails.getTitle());
        intent.putExtra(DetailsFragment.MOVIE_OVERVIEW, movieDetails.getOverview());
        intent.putExtra(DetailsFragment.MOVIE_VOTE_AVERAGE, movieDetails.getVoteAverage());
        intent.putExtra(DetailsFragment.MOVIE_RELEASE_DATE, movieDetails.getReleaseDate());
        intent.putExtra(DetailsFragment.MOVIE_POSTER_URL, movieDetails.getPosterUrl());
        return intent;
    }
}

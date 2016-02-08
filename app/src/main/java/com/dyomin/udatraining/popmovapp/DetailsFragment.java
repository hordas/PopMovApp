package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;
import com.dyomin.udatraining.popmovapp.data.review.Review;
import com.dyomin.udatraining.popmovapp.data.trailer.Trailer;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;
import com.squareup.picasso.Picasso;
import java.util.List;

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

    private LinearLayout trailersListView;
    private LinearLayout reviewsListView;
    private ProgressBar progressBarTrailers;
    private ProgressBar progressBarReviews;

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
        trailersListView = (LinearLayout) v.findViewById(R.id.linearlayout_trailers);
        reviewsListView = (LinearLayout) v.findViewById(R.id.linearlayout_reviews);
        progressBarTrailers = (ProgressBar) v.findViewById(R.id.progressbar_trailers);
        progressBarReviews = (ProgressBar) v.findViewById(R.id.progressbar_reviews);
        Bundle args = getArguments();
        int movieId = args.getInt(MOVIE_TMDB_ID);

        Picasso.with(getActivity()).load(
                        Connection.getImageUrl(args.getString(MOVIE_POSTER_URL))
                ).into(posterView);
                title.setText(args.getString(MOVIE_TITLE));
                overview.setText(args.getString(MOVIE_OVERVIEW));
                voteAverage.setText(args.getString(MOVIE_VOTE_AVERAGE));
                releaseDate.setText(args.getString(MOVIE_RELEASE_DATE));

        String movieIdString = Integer.toString(movieId);
        new TrailersUploader().execute(movieIdString);
        new ReviewsUploader().execute(movieIdString);
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

    public class TrailersUploader extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            progressBarTrailers.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... movieIds) {
            String movieId = movieIds[0];
            return Connection.processRequest(Connection.getTrailersUrl(movieId));
        }

        @Override
        protected void onPostExecute(String responseString) {
            progressBarTrailers.setVisibility(View.INVISIBLE);
            if (responseString != null) {
                List<Trailer> trailers = JsonParser.parseTrailers(responseString);
                if (trailers.size() > 0) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    for (int i = 0; i < trailers.size(); i++) {
                        View trailerView = inflater.inflate(R.layout.trailer_item, null);
                        TextView titleTextView = (TextView) trailerView.findViewById(R.id.textview_trailer_name);
                        ImageButton imageButton = (ImageButton) trailerView.findViewById(R.id.imagebutton_play);
                        imageButton.setOnClickListener(listener);
                        imageButton.setTag(trailers.get(i).getTrailerKey());
                        titleTextView.setText(trailers.get(i).getName());
                        trailersListView.addView(trailerView);
                    }
                }
            }
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String path = (String) v.getTag();
            if (!TextUtils.isEmpty(path)) {
                String youtubeUrl = "http://www.youtube.com/watch?v=" + path;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                getActivity().startActivity(intent);
            }
        }
    };

    public class ReviewsUploader extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            progressBarReviews.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... movieIds) {
            String movieId = movieIds[0];
            return Connection.processRequest(Connection.getReviewsUrl(movieId));
        }

        @Override
        protected void onPostExecute(String responseString) {
            progressBarReviews.setVisibility(View.INVISIBLE);
            if (responseString != null) {
                List<Review> reviews = JsonParser.parseReviews(responseString);
                if (reviews.size() > 0) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    for (int i = 0; i < reviews.size(); i++) {
                        View reviewView = inflater.inflate(R.layout.review_item, null);
                        TextView author = (TextView) reviewView.findViewById(R.id.textview_author);
                        TextView content = (TextView) reviewView.findViewById(R.id.textview_content);
                        author.setText(reviews.get(i).getAuthor());
                        content.setText(reviews.get(i).getContent());
                        reviewsListView.addView(reviewView);
                    }
                }
            }
        }
    }
}

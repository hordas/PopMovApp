package com.dyomin.udatraining.popmovapp;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;
import com.dyomin.udatraining.popmovapp.data.review.Review;
import com.dyomin.udatraining.popmovapp.data.trailer.Trailer;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieColumns;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieContentValues;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieCursor;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieSelection;
import com.dyomin.udatraining.popmovapp.provider.review.ReviewColumns;
import com.dyomin.udatraining.popmovapp.provider.review.ReviewContentValues;
import com.dyomin.udatraining.popmovapp.provider.video.VideoColumns;
import com.dyomin.udatraining.popmovapp.provider.video.VideoContentValues;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Vector;

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
    private ToggleButton toggleFavorite;

    private boolean favorite;
    private MovieDetails movie;
    private List<Trailer> trailers;
    private List<Review> reviews;

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
        toggleFavorite = (ToggleButton) v.findViewById(R.id.button_starred);

        movie = getMovieDataFromTheBundle(getArguments());
        checkIsFavorite();


        Picasso.with(getActivity()).load(
                        Connection.getImageUrl(movie.getPosterUrl())
                ).into(posterView);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        voteAverage.setText(movie.getVoteAverage());
        releaseDate.setText(movie.getReleaseDate());

        obtainTrailersAndReviews();
        toggleFavorite.setOnCheckedChangeListener(buttonFavoriteListener);
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

    private static MovieDetails getMovieDataFromTheBundle(Bundle args) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setMovieId(args.getInt(MOVIE_TMDB_ID));
        movieDetails.setTitle(args.getString(MOVIE_TITLE));
        movieDetails.setOverview(args.getString(MOVIE_OVERVIEW));
        movieDetails.setVoteAverage(args.getString(MOVIE_VOTE_AVERAGE));
        movieDetails.setReleaseDate(args.getString(MOVIE_RELEASE_DATE));
        movieDetails.setPosterUrl(args.getString(MOVIE_POSTER_URL));
        return movieDetails;
    }

    private View.OnClickListener buttonPlayListener = new View.OnClickListener() {
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

    private ToggleButton.OnCheckedChangeListener buttonFavoriteListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            toggleFavorite.setEnabled(false);

            if (isChecked) {
                writeToDB();
            } else {
                removeFromDB();
            }
            favorite = isChecked;

            toggleFavorite.setEnabled(true);
        }
    };

    private void checkIsFavorite() {
        int movieId = movie.getMovieId();
        MovieSelection where = new MovieSelection();
        MovieCursor mvCursor = where.tmdbId(movieId).query(getContext().getContentResolver());
        favorite = false;
        if (mvCursor != null) {
            favorite = mvCursor.moveToFirst();
        }
        toggleFavorite.setChecked(favorite);
    }

    private void obtainTrailersAndReviews() {
        String movieIdString = Integer.toString(movie.getMovieId());
        new TrailersUploader().execute(movieIdString);
        new ReviewsUploader().execute(movieIdString);
    }

    private void removeFromDB() {
        Toast.makeText(getContext(), "Count of records before is: " + getCountOfMovies(), Toast.LENGTH_LONG).show();
        MovieSelection where = new MovieSelection();
        where.tmdbId(movie.getMovieId()).delete(getContext().getContentResolver());
        Toast.makeText(getContext(), "Count of records after is: " + getCountOfMovies(), Toast.LENGTH_LONG).show();
    }

    private int getCountOfMovies() {
        return new MovieSelection().query(getContext().getContentResolver()).getCount();
    }

    private void writeToDB() {
        MovieContentValues movieContentValues = new MovieContentValues();
        movieContentValues.putTmdbId(movie.getMovieId())
                .putOriginalTitle(movie.getTitle())
                .putOverview(movie.getOverview())
                .putVoteAverage(Float.parseFloat(movie.getVoteAverage()))
                .putMovieReleaseDate(movie.getReleaseDate())
                .putMoviePosterUri(movie.getPosterUrl());
        Uri insertedRowUri = getContext().getContentResolver()
                .insert(MovieColumns.CONTENT_URI, movieContentValues.values());


        if (insertedRowUri != null && insertedRowUri.getLastPathSegment() != null) {
                String idPath = insertedRowUri.getLastPathSegment();
                Integer movieDatabaseID = Integer.parseInt(idPath);
                writeTrailersToDB(movieDatabaseID);
                writeReviewsToDB(movieDatabaseID);
        } else {
            Log.d(">>>> " + DetailsFragment.class.getSimpleName(),
                    "Unable to write trailers and reviews to database!");
        }

    }

    private void writeTrailersToDB(int movieDatabaseId) {
        if (trailers != null && trailers.size() > 0) {
            Vector<ContentValues> trailersVector = new Vector<>();
            for (Trailer trailer : trailers) {
                VideoContentValues videoCV = new VideoContentValues();
                videoCV.putMovieId(movieDatabaseId)
                        .putName(trailer.getName())
                        .putTrailerKey(trailer.getTrailerKey());
                trailersVector.add(videoCV.values());
            }
            ContentValues[] cvArray = new ContentValues[trailersVector.size()];
            trailersVector.toArray(cvArray);
            getContext().getContentResolver().bulkInsert(VideoColumns.CONTENT_URI, cvArray);
        }
    }

    private void writeReviewsToDB(int movieDatabaseId) {
        if (reviews != null && reviews.size() > 0) {
            Vector<ContentValues> reviewsVector = new Vector<>();
            for (Review review : reviews) {
                ReviewContentValues reviewCV = new ReviewContentValues();
                reviewCV.putMovieId(movieDatabaseId)
                        .putAuthor(review.getAuthor())
                        .putContent(review.getContent());
                reviewsVector.add(reviewCV.values());
            }
            ContentValues[] cvArray = new ContentValues[reviewsVector.size()];
            reviewsVector.toArray(cvArray);
            getContext().getContentResolver().bulkInsert(ReviewColumns.CONTENT_URI, cvArray);
        }
    }

    private class TrailersUploader extends AsyncTask<String, Void, String> {

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
                trailers = JsonParser.parseTrailers(responseString);
                if (trailers.size() > 0) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    for (int i = 0; i < trailers.size(); i++) {
                        View trailerView = inflater.inflate(R.layout.trailer_item, null);
                        TextView titleTextView = (TextView) trailerView.findViewById(R.id.textview_trailer_name);
                        ImageButton imageButton = (ImageButton) trailerView.findViewById(R.id.imagebutton_play);
                        imageButton.setOnClickListener(buttonPlayListener);
                        imageButton.setTag(trailers.get(i).getTrailerKey());
                        titleTextView.setText(trailers.get(i).getName());
                        trailersListView.addView(trailerView);
                    }
                }
            }
        }

    }

    private class ReviewsUploader extends AsyncTask<String, Void, String> {

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
                reviews = JsonParser.parseReviews(responseString);
                if (reviews.size() > 0) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    for (Review review : reviews) {
                        View reviewView = inflater.inflate(R.layout.review_item, null);
                        TextView author = (TextView) reviewView.findViewById(R.id.textview_author);
                        TextView content = (TextView) reviewView.findViewById(R.id.textview_content);
                        author.setText(review.getAuthor());
                        content.setText(review.getContent());
                        reviewsListView.addView(reviewView);
                    }
                }
            }
        }
    }
}

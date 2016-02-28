package com.dyomin.udatraining.popmovapp;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.dyomin.udatraining.popmovapp.provider.review.ReviewCursor;
import com.dyomin.udatraining.popmovapp.provider.review.ReviewSelection;
import com.dyomin.udatraining.popmovapp.provider.video.VideoColumns;
import com.dyomin.udatraining.popmovapp.provider.video.VideoContentValues;
import com.dyomin.udatraining.popmovapp.provider.video.VideoCursor;
import com.dyomin.udatraining.popmovapp.provider.video.VideoSelection;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static com.dyomin.udatraining.popmovapp.FragmentInterconnectionHelper.*;

/**
 * Contains information about selected movie.
 */
public class DetailsFragment extends Fragment {

    private static final String POPMOVAPP_SHARE_HASHTAG = "#PopularMoviesApp";
    public static final String BASE_YOUTUBE_URL = "http://www.youtube.com/watch?v=";

    private LinearLayout trailersListView;
    private LinearLayout reviewsListView;
    private ProgressBar progressBarTrailers;
    private ProgressBar progressBarReviews;
    private ToggleButton toggleFavorite;
    private RelativeLayout trailersLayout;
    private RelativeLayout reviewsLayout;

    private boolean favorite;
    private MovieDetails movie;
    private List<Trailer> trailers;
    private List<Review> reviews;
    private ShareActionProvider mShareActionProvider;
    private String movieInfo;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieInfo = "";
        setHasOptionsMenu(true);
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
        trailersLayout = (RelativeLayout) v.findViewById(R.id.trailers_layout);
        reviewsLayout = (RelativeLayout) v.findViewById(R.id.reviews_layout);

        Bundle args = getArguments();
        movie = getMovieDataFromTheBundle(args);
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

    private void initMovieInfoAndShareProvider() {
        if (movie != null && trailers != null && trailers.size() > 0) {
            movieInfo = movie.getTitle()
                    + "\nrelease: " + movie.getReleaseDate()
                    + "\n" + BASE_YOUTUBE_URL + trailers.get(0).getTrailerKey()
                    + "\n" + POPMOVAPP_SHARE_HASHTAG;
        } else {
            movieInfo = "";
        }
        initShareProvider();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details_fragment, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        initShareProvider();
    }

    private void initShareProvider() {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareMovieIntent());
        }
    }

    private Intent createShareMovieIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, movieInfo);
        return shareIntent;
    }

    private View.OnClickListener buttonPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String path = (String) v.getTag();
            if (!TextUtils.isEmpty(path)) {
                String youtubeUrl = BASE_YOUTUBE_URL + path;
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
        if (!favorite) {
            new TrailersUploader().execute(movieIdString);
            new ReviewsUploader().execute(movieIdString);
        } else {
            obtainTrailersAndReviewsFromDB();
        }
    }

    private void obtainTrailersAndReviewsFromDB() {
        MovieCursor cursor = new MovieSelection().tmdbId(movie.getMovieId()).query(getContext().getContentResolver());
        if (cursor.moveToFirst()) {
            int movieId = (int) cursor.getId();
            readTrailersFromDB(movieId);
            readReviewsFromDB(movieId);
        }
    }

    private void readTrailersFromDB(int movieId) {
        VideoSelection where = new VideoSelection();
        VideoCursor cursor = where.movieId(movieId).query(getContext().getContentResolver());
        trailers = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Trailer trailer = new Trailer();
                trailer.setMovieTmdbId(cursor.getMovieTmdbId());
                trailer.setTrailerKey(cursor.getTrailerKey());
                trailer.setName(cursor.getName());
                trailers.add(trailer);
            }
            initMovieInfoAndShareProvider();
        }
        refreshTrailersListview();
    }

    private void readReviewsFromDB(int movieId) {
        ReviewSelection where = new ReviewSelection();
        ReviewCursor cursor = where.movieId(movieId).query(getContext().getContentResolver());
        reviews = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Review review = new Review();
                review.setMovieTmdbId(cursor.getMovieTmdbId());
                review.setAuthor(cursor.getAuthor());
                review.setContent(cursor.getContent());
                reviews.add(review);
            }
        }
        refreshReviewsListview();
    }

    private void removeFromDB() {
        MovieSelection where = new MovieSelection();
        where.tmdbId(movie.getMovieId()).delete(getContext().getContentResolver());
        sendRemovingIntent();
    }

    private void sendRemovingIntent() {
        Intent removingIntent = new Intent(MainActivity.TMDB_MOVIE_REMOVED);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(removingIntent);
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

    class TrailersUploader extends AsyncTask<String, Void, String> {

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
                refreshTrailersListview();
            }
        }
    }

    private void refreshTrailersListview() {
        if (trailers.size() > 0) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (int i = 0; i < trailers.size(); i++) {
                View trailerView = inflater.inflate(R.layout.trailer_item, trailersListView);
                TextView titleTextView = (TextView) trailerView.findViewById(R.id.textview_trailer_name);
                ImageButton imageButton = (ImageButton) trailerView.findViewById(R.id.imagebutton_play);
                imageButton.setOnClickListener(buttonPlayListener);
                imageButton.setTag(trailers.get(i).getTrailerKey());
                titleTextView.setText(trailers.get(i).getName());
                trailersListView.addView(trailerView);
            }
            initMovieInfoAndShareProvider();
            trailersLayout.setVisibility(View.VISIBLE);
        } else {
            trailersLayout.setVisibility(View.GONE);
        }
    }

    class ReviewsUploader extends AsyncTask<String, Void, String> {

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
            if (responseString == null) {
                return;
            }
            reviews = JsonParser.parseReviews(responseString);
            refreshReviewsListview();
        }
    }

    private void refreshReviewsListview() {
        if (reviews.size() > 0) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (Review review : reviews) {
                View reviewView = inflater.inflate(R.layout.review_item, reviewsListView);
                TextView author = (TextView) reviewView.findViewById(R.id.textview_author);
                TextView content = (TextView) reviewView.findViewById(R.id.textview_content);
                author.setText(review.getAuthor());
                content.setText(review.getContent());
                reviewsListView.addView(reviewView);
            }
            reviewsLayout.setVisibility(View.VISIBLE);
        } else {
            reviewsLayout.setVisibility(View.GONE);
        }
    }
}

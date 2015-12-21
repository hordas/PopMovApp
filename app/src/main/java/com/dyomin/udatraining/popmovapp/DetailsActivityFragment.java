package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dyomin.udatraining.popmovapp.data.MovieDetails;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;
import com.squareup.picasso.Picasso;


/**
 * Contains information about selected movie.
 */
public class DetailsActivityFragment extends Fragment {

    private ImageView posterView;
    private TextView releaseDate;
    private TextView voteAverage;
    private TextView overview;
    private TextView title;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        posterView = (ImageView) v.findViewById(R.id.imageview_movie_poster);
        title = (TextView) v.findViewById(R.id.textview_original_title);
        overview = (TextView) v.findViewById(R.id.textview_movie_synopsis);
        voteAverage = (TextView) v.findViewById(R.id.textview_movie_rating);
        releaseDate = (TextView) v.findViewById(R.id.textview_movie_release_date);

        String movieId = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
        String movieUrl = Connection.getMovieUrl(movieId);
        MovieDetailsDownloader mdd = new MovieDetailsDownloader();
        mdd.execute(movieUrl);
        return v;
    }

    public class MovieDetailsDownloader extends AsyncTask<String, Void, MovieDetails> {

        private final String LOG_TAG = MovieDetailsDownloader.class.getSimpleName();

        @Override
        protected MovieDetails doInBackground(String... params) {
            String url = params[0];
            return JsonParser.parseMovieDetails(Connection.processRequest(url));
        }

        @Override
        protected void onPostExecute(MovieDetails movieDetails) {
            if (movieDetails != null) {
                Picasso.with(getActivity()).load(
                        Connection.getImageUrl(movieDetails.getPosterPath())
                ).into(posterView);
                title.setText(movieDetails.getTitle());
                overview.setText(movieDetails.getOverview());
                voteAverage.setText(movieDetails.getVoteAverage());
                releaseDate.setText(movieDetails.getReleaseDate());
            } else {
                Toast.makeText(getActivity(),
                        "Unable to load movie information. Actual results is NULL!",
                        Toast.LENGTH_LONG).show();
                Log.d(LOG_TAG, "Unable to load movie information. Actual results is NULL!");
            }
        }
    }
}

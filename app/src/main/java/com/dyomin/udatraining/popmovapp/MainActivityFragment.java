package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;
import com.dyomin.udatraining.popmovapp.data.poster.PosterAdapter;
import com.dyomin.udatraining.popmovapp.data.poster.PosterBatch;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieCursor;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieSelection;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment {

    private static final int MOVIES_PER_PAGE = 20;

    private PosterAdapter posterAdapter;
    private GridView gv;
    private TextView buttonLeft;
    private TextView buttonRight;
    private TextView textViewCurrentPage;
    private PostersUploader postersUploader;
    private ProgressBar progressBarPosters;

    private int totalPages;
    private int currentPage;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            currentPage = 1;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateResults();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ma_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                stopTaskIfRunning();
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void stopTaskIfRunning() {
        if (postersUploader != null) {
            if (postersUploader.getStatus().equals(AsyncTask.Status.RUNNING)) {
                postersUploader.cancel(true);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        gv = (GridView) v.findViewById(R.id.gridview);
        buttonLeft = (TextView) v.findViewById(R.id.textview_left_arrow);
        buttonRight = (TextView) v.findViewById(R.id.textview_right_arrow);
        textViewCurrentPage = (TextView) v.findViewById(R.id.textview_current_page);
        progressBarPosters = (ProgressBar) v.findViewById(R.id.progressbar_posters);
        updateResults();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Callback callback = (Callback) getActivity();
                stopTaskIfRunning();
                callback.onItemSelected((MovieDetails) posterAdapter.getItem(position));
            }
        });
        //todo - add handling for "favorite" case
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage != 1) {
                    String selectionPreference = getSelectionPreference();
                    if (!selectionPreference.equals(getString(R.string.movies_sort_favorites))) {
                        stopTaskIfRunning();
                        String url = Connection.getCertainPageUrl(getSelectionPreference(),
                                (currentPage - 1));
                        updateResults(url);
                    } else {
                        currentPage--;
                        processFavoriteOption();
                    }
                }
            }
        });
        //todo - add handling for "favorite" case
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage != totalPages) {
                    String selectionPreference = getSelectionPreference();
                    if (!selectionPreference.equals(getString(R.string.movies_sort_favorites))) {
                        stopTaskIfRunning();
                        String url = Connection.getCertainPageUrl(getSelectionPreference(),
                                (currentPage + 1));
                        updateResults(url);
                    } else {
                        currentPage++;
                        processFavoriteOption();
                    }
                }
            }
        });

        return v;
    }

    private void updateResults(String url) {
        postersUploader = new PostersUploader();
        postersUploader.execute(url);
    }

    private void updateResults() {
        String selectionPreference = getSelectionPreference();
        if (!selectionPreference.equals(getString(R.string.movies_sort_favorites))) {
            updateResults(Connection.getMoviesUrl(selectionPreference));
        } else {
            processFavoriteOption();
        }
    }

    private void processFavoriteOption() {
        MovieSelection where = new MovieSelection();
        MovieCursor cursor = where.query(getContext().getContentResolver());
        if (cursor != null && cursor.getCount() > 0) {
            int totalRecords = cursor.getCount();
            totalPages = totalRecords / MOVIES_PER_PAGE;
            if (totalPages % MOVIES_PER_PAGE > 0) {
                totalPages += 1;
            }
//            currentPage = 1;
            PosterBatch batch = new PosterBatch();
            List<MovieDetails> moviesList = new ArrayList<>();
            int currentShift = (currentPage - 1) * MOVIES_PER_PAGE;
            cursor.moveToPosition(currentShift - 1);
            for (int i = currentShift; i < currentShift + MOVIES_PER_PAGE && cursor.moveToNext(); i++) {
                MovieDetails details = new MovieDetails();
                details.setMovieId(cursor.getTmdbId());
                details.setTitle(cursor.getOriginalTitle());
                details.setOverview(cursor.getOverview());
                details.setReleaseDate(cursor.getMovieReleaseDate());
                details.setVoteAverage(Float.toString(cursor.getVoteAverage()));
                details.setPosterUrl(cursor.getMoviePosterUri());
                moviesList.add(details);
            }
            batch.setCurrentPage(currentPage);
            batch.setTotalPages(totalPages);
            batch.setMovieDetailses(moviesList);
            setBatchResults(batch);
        }
    }

    private String getSelectionPreference() {
        return PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.pref_key_popular_movies_sort_order),
                        getString(R.string.movies_sort_order_default));
    }

    public class PostersUploader extends AsyncTask<String, Void, PosterBatch> {

        private final String LOG_TAG = PostersUploader.class.getSimpleName();

        protected void onPreExecute() {
            setPageNavigatorLoading(true);
        }

        @Override
        protected PosterBatch doInBackground(String ... params) {
            String url = params[0];
            return JsonParser.parseMovies(Connection.processRequest(url));
        }

        @Override
        protected void onPostExecute(PosterBatch posterBatch) {
            setPageNavigatorLoading(false);
            if (posterBatch == null) {
                throw new RuntimeException(LOG_TAG + "PosterBatch == null.");
            }
            setBatchResults(posterBatch);
        }
    }

    private void setPageNavigatorLoading(boolean loading) {
        int preLoaderMode = loading ? View.VISIBLE : View.INVISIBLE;
        int paginatorMode = loading ? View.INVISIBLE : View.VISIBLE;

        progressBarPosters.setVisibility(preLoaderMode);
        textViewCurrentPage.setVisibility(paginatorMode);
        buttonLeft.setVisibility(paginatorMode);
        buttonRight.setVisibility(paginatorMode);
    }

    private void setBatchResults(PosterBatch batch) {
        if (posterAdapter != null) {
            posterAdapter.updateResults(batch.getMovieDetailses());
        } else {
            posterAdapter = new PosterAdapter(getActivity(), batch.getMovieDetailses());
            gv.setAdapter(posterAdapter);
        }
        setPages(batch);
        sendBootIntent(batch.getMovieDetailses().get(0));
    }

    private void sendBootIntent(MovieDetails movieDetails) {
        Intent bootIntent = new Intent(MainActivity.TMDB_RESULTS_UPDATED);
        bootIntent = DetailsFragment.puDataIntoIntent(bootIntent, movieDetails);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(bootIntent);
    }

    private void setPages(PosterBatch batch) {
        currentPage = batch.getCurrentPage();
        totalPages = batch.getTotalPages();
        textViewCurrentPage.setText(Integer.toString(currentPage));
        updateLeftButton();
        updateRightButton();
    }

    private void updateLeftButton() {
        if (currentPage == 1) {
            buttonLeft.setVisibility(View.INVISIBLE);
        } else {
            buttonLeft.setVisibility(View.VISIBLE);
        }
    }

    private void updateRightButton() {
        if (currentPage == totalPages) {
            buttonRight.setVisibility(View.INVISIBLE);
        } else {
            buttonRight.setVisibility(View.VISIBLE);
        }
    }

    public interface Callback {
        void onItemSelected(MovieDetails movieDetails);
    }
}

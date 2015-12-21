package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.dyomin.udatraining.popmovapp.data.PosterAdapter;
import com.dyomin.udatraining.popmovapp.data.PosterBatch;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.dyomin.udatraining.popmovapp.util.JsonParser;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment {

    private PosterAdapter posterAdapter;
    private GridView gv;
    private TextView buttonLeft;
    private TextView buttonRight;
    private TextView textViewCurrentPage;
    private PostersUploader pu;

    private int totalPages;
    private int currentPage;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        if (pu.getStatus().equals(AsyncTask.Status.RUNNING)) {
            pu.cancel(true);
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
        updateResults();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stopTaskIfRunning();
                String filmId = posterAdapter.getIdByPosition(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, filmId);
                startActivity(intent);
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage != 1) {
                    stopTaskIfRunning();
                    String url = Connection.getCertainPageUrl(getSortOrder(),
                            (currentPage - 1));
                    updateResults(url);
                }
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage != totalPages) {
                    stopTaskIfRunning();
                    String url = Connection.getCertainPageUrl(getSortOrder(),
                            (currentPage + 1));
                    updateResults(url);
                }
            }
        });

        return v;
    }

    private void updateResults(String url) {
        pu = new PostersUploader();
        pu.execute(url);
    }

    private void updateResults() {
        updateResults(Connection.getMoviesUrl(getSortOrder()));
    }

    private String getSortOrder() {
        return PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.pref_key_popular_movies_sort_order),
                        getString(R.string.movies_sort_order_default));
    }

    public class PostersUploader extends AsyncTask<String, Void, PosterBatch> {

        private final String LOG_TAG = PostersUploader.class.getSimpleName();

        @Override
        protected PosterBatch doInBackground(String ... params) {
            String url = params[0];
            return JsonParser.parseTopMovies(Connection.processRequest(url));
        }

        @Override
        protected void onPostExecute(PosterBatch posterBatch) {
            if (posterBatch != null) {
                setBatchResults(posterBatch);
            } else {
                throw new RuntimeException(LOG_TAG + "PosterBatch == null.");
            }
        }
    }

    private void setBatchResults(PosterBatch batch) {
        if (posterAdapter != null) {
            posterAdapter.updateResults(batch.getPosters());
            setPages(batch);
        } else {
            posterAdapter = new PosterAdapter(getActivity(), batch.getPosters());
            gv.setAdapter(posterAdapter);
            setPages(batch);
        }
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
}

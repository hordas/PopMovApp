package com.dyomin.udatraining.popmovapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private PosterAdapter posterAdapter;
    private GridView gv;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ma_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PostersUploader tu;
        switch (item.getItemId()) {
            case R.id.action_sort_asc:
                tu = new PostersUploader();
                tu.execute(Connection.getPopularMoviesUrl(true));
                return true;

            case R.id.action_sort_desc:
                tu = new PostersUploader();
                tu.execute(Connection.getPopularMoviesUrl(false));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        gv = (GridView) v.findViewById(R.id.gridview);
        PostersUploader tu = new PostersUploader();
        tu.execute(Connection.getPopularMoviesUrl(false));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filmId = posterAdapter.getIdByPosition(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, filmId);
                startActivity(intent);
            }
        });
        return v;
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
    }

}

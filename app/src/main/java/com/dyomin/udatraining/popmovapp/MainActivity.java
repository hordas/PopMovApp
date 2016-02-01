package com.dyomin.udatraining.popmovapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieSelection;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements MainActivityFragment.Callback{

    public static final String TMDB_RESULTS_UPDATED = "tmdb_results_updated";

    private boolean twoPane;
    private BroadcastReceiver bootReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int movieId = intent.getIntExtra(DetailsActivityFragment.MOVIE_TMDB_ID, -1);
            onItemSelected(movieId);
        }
    };

    @Override
    protected void onDestroy() {
        if (twoPane) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(bootReceiver);
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.details_fragment) != null) {
            twoPane = true;
            LocalBroadcastManager.getInstance(this).registerReceiver(
                    bootReceiver, new IntentFilter(TMDB_RESULTS_UPDATED));
        } else {
            twoPane = false;
        }
    }

    @Override
    public void onItemSelected(int movieId) {
        MovieSelection where = new MovieSelection();
        where.tmdbId(movieId);

        if (twoPane) {
            Bundle args = new Bundle();
            args.putInt(DetailsActivityFragment.MOVIE_TMDB_ID, movieId);

            DetailsActivityFragment fragment = new DetailsActivityFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment).commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, movieId);
            startActivity(intent);
        }
    }
}
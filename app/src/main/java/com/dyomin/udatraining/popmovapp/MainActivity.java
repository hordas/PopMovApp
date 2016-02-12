package com.dyomin.udatraining.popmovapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;

import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements MainActivityFragment.Callback{

    public static final String TMDB_RESULTS_UPDATED = "tmdb_results_updated";

    private boolean twoPane;
    private BroadcastReceiver bootReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onItemSelected(DetailsFragment.createPosterFromIntentData(intent));
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
    public void onItemSelected(MovieDetails movieDetails) {
        if (twoPane) {
            Bundle args = DetailsFragment.putDataIntoTheBundle(movieDetails);
            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment).commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent = DetailsFragment.puDataIntoIntent(intent, movieDetails);
            startActivity(intent);
        }
    }
}
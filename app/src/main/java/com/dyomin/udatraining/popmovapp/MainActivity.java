package com.dyomin.udatraining.popmovapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;

import static com.dyomin.udatraining.popmovapp.FragmentInterconnectionHelper.*;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements MainActivityFragment.Callback{

    public static final String TMDB_RESULTS_UPDATED = "tmdb_results_updated";
    public static final String TMDB_MOVIE_REMOVED = "tmdb_movie_removed";

    private boolean twoPane;
    private BroadcastReceiver bootReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case TMDB_RESULTS_UPDATED:
                    MovieDetails movieDetails = null;
                    if (intent.getIntExtra(MOVIE_TMDB_ID, NOT_PRESENT) != NOT_PRESENT) {
                        movieDetails = createPosterFromIntentData(intent);
                    }
                    onItemSelected(movieDetails);
                    break;

                case TMDB_MOVIE_REMOVED:
                    MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.fragment);
                    fragment.refreshMoviesAfterRemoving();
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "Unknown action: " + intent.getAction(),
                            Toast.LENGTH_LONG).show();
                    break;
            }
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
            IntentFilter filter = new IntentFilter(TMDB_RESULTS_UPDATED);
            filter.addAction(TMDB_MOVIE_REMOVED);
            LocalBroadcastManager.getInstance(this).registerReceiver(
                    bootReceiver, filter);
        } else {
            twoPane = false;
        }
    }

    @Override
    public void onItemSelected(MovieDetails movieDetails) {
        if (twoPane) {
            updateDetailsFragmentForTwoPane(movieDetails);
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent = puDataIntoIntent(intent, movieDetails);
            startActivity(intent);
        }
    }

    private void updateDetailsFragmentForTwoPane(MovieDetails movieDetails) {
        if (movieDetails != null) {
            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(putDataIntoTheBundle(movieDetails));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment).commit();
        } else {
            DetailsFragment detailsFragment =
                    (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_fragment);
            if (detailsFragment != null) {
                getSupportFragmentManager().beginTransaction().detach(detailsFragment).commit();
            }
        }
    }
}
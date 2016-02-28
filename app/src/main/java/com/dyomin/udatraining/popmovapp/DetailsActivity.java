package com.dyomin.udatraining.popmovapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import static com.dyomin.udatraining.popmovapp.FragmentInterconnectionHelper.*;

/*
 * Activity for details info about selected movie.
 */

@SuppressWarnings("deprecation")
public class DetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null) {
            Bundle args = createBundleFromDetailsIntent(getIntent());

            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.framelayout_movie_details, fragment).commit();
        }
    }
}

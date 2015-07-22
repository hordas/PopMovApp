package com.dyomin.udatraining.popmovapp;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 19.07.2015.
 */
public class Connection {

    private static final String BASE_API_URL = "https://api.themoviedb.org/3";
    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w342";
    private static final String API_KEY_VALUE = NoCommit.API_KEY;
    private static final String API_KEY_KEY = "api_key";
    private static final String MOVIE_PART = "discover/movie";
    private static final String MOVIE_FOR_ID_PART = "movie/";
    private static final String SORT_BY_KEY = "sort_by";
    private static final String SORT_BY_POPULARITY = "popularity.";
    private static final String DESC = "desc";
    private static final String ASC = "asc";

    private static final String LOG_TAG = Connection.class.getSimpleName();

    public static String processRequest(String inputUrl) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJsonString = null;
        Log.d(LOG_TAG, "requested: " + inputUrl);
        try {

            URL url = new URL(inputUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            String line;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            resultJsonString = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return resultJsonString;
    }

    public static String getPopularMoviesUrl(boolean isAscSortOrder) {
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendEncodedPath(MOVIE_PART)
                .appendQueryParameter(SORT_BY_KEY, SORT_BY_POPULARITY
                        + (isAscSortOrder ? ASC : DESC))
                .appendQueryParameter(API_KEY_KEY, API_KEY_VALUE).build();
        return uri.toString();
    }

    public static String getMovieUrl(String id) {
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendEncodedPath(MOVIE_FOR_ID_PART + id)
                .appendQueryParameter(API_KEY_KEY, API_KEY_VALUE).build();
        return uri.toString();
    }

    public static String getImageUrl(String imagePathPart) {
        Uri uri = Uri.parse(BASE_IMAGE_URL).buildUpon()
                .appendEncodedPath(imagePathPart).build();
        return uri.toString();
    }
}

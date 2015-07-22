package com.dyomin.udatraining.popmovapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 19.07.2015.
 */
public class JsonParser {

    public static PosterBatch parseTopMovies(String response) {
        PosterBatch batch = new PosterBatch();
        List<Poster> posters = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray results = responseObject.getJSONArray("results");
            batch.setCurrentPage(responseObject.getInt("page"));
            for (int i = 0; i < results.length(); i++) {
                JSONObject filmInfo = results.getJSONObject(i);
                Poster poster = new Poster();
                poster.setFilmId(Integer.toString(filmInfo.getInt("id")));
                poster.setPosterUrl(filmInfo.getString("poster_path"));
                posters.add(poster);
            }
            batch.setPosters(posters);
            batch.setTotalPages(responseObject.getInt("total_pages"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return batch;
    }

    public static MovieDetails parseMovieDetails(String response) {
        MovieDetails movie = new MovieDetails();
        try {
            JSONObject responseObject = new JSONObject(response);
            movie.setTitle(responseObject.getString("original_title"));
            movie.setOverview(responseObject.getString("overview"));
            movie.setPosterPath(responseObject.getString("poster_path"));
            movie.setReleaseDate(responseObject.getString("release_date"));
            movie.setVoteAverage(responseObject.getString("vote_average"));
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return movie;
    }
}

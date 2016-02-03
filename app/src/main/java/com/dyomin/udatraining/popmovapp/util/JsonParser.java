package com.dyomin.udatraining.popmovapp.util;

import com.dyomin.udatraining.popmovapp.data.MovieDetails;
import com.dyomin.udatraining.popmovapp.data.Review;
import com.dyomin.udatraining.popmovapp.data.Trailer;
import com.dyomin.udatraining.popmovapp.data.poster.Poster;
import com.dyomin.udatraining.popmovapp.data.poster.PosterBatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Class parses JSON responses for movie details and for main activity.
 * --
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

    @SuppressWarnings("unused")
    public static List<Trailer> parseTrailers(String response) {
        List<Trailer> resultList = new ArrayList<>();
        try {
            JSONObject rootObj = new JSONObject(response);
            int id = rootObj.getInt("id");
            JSONArray trailersJSONArray = rootObj.getJSONArray("results");
            for (int i = 0; i < trailersJSONArray.length(); i++) {
                JSONObject trailerJSON = trailersJSONArray.getJSONObject(i);
                Trailer trailer = new Trailer();
                trailer.setMovieTmdbId(id);
                trailer.setName(trailerJSON.getString("name"));
                trailer.setTrailerKey(trailerJSON.getString("key"));
                resultList.add(trailer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @SuppressWarnings("unused")
    public static List<Review> parseReviews(String response) {
        List<Review> resultList = new ArrayList<>();
        try {
            JSONObject rootObj = new JSONObject(response);
            int id = rootObj.getInt("id");
            JSONArray trailersJSONArray = rootObj.getJSONArray("results");
            for (int i = 0; i < trailersJSONArray.length(); i++) {
                JSONObject reviewJSON = trailersJSONArray.getJSONObject(i);
                Review review = new Review();
                review.setMovieTmdbId(id);
                review.setAuthor(reviewJSON.getString("author"));
                review.setAuthor(reviewJSON.getString("content"));
                resultList.add(review);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

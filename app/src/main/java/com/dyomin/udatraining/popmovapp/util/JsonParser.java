package com.dyomin.udatraining.popmovapp.util;

import com.dyomin.udatraining.popmovapp.data.poster.MovieDetails;
import com.dyomin.udatraining.popmovapp.data.review.Review;
import com.dyomin.udatraining.popmovapp.data.trailer.Trailer;
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

    public static PosterBatch parseMovies(String response) {
        PosterBatch batch = new PosterBatch();
        List<MovieDetails> movieDetailses = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray results = responseObject.getJSONArray("results");
            batch.setCurrentPage(responseObject.getInt("page"));
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                MovieDetails movieDetails = parseMovieDetails(movie);
                movieDetailses.add(movieDetails);
            }
            batch.setMovieDetailses(movieDetailses);
            batch.setTotalPages(responseObject.getInt("total_pages"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return batch;
    }

    public static MovieDetails parseMovieDetails(JSONObject responseObject) {
        MovieDetails movie = new MovieDetails();
        try {
            movie.setMovieId(responseObject.getInt("id"));
            movie.setTitle(responseObject.getString("original_title"));
            movie.setOverview(responseObject.getString("overview"));
            movie.setPosterUrl(responseObject.getString("poster_path"));
            movie.setReleaseDate(responseObject.getString("release_date"));
            movie.setVoteAverage(responseObject.getString("vote_average"));
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return movie;
    }

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
                review.setContent(reviewJSON.getString("content"));
                resultList.add(review);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

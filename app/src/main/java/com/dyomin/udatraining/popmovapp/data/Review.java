package com.dyomin.udatraining.popmovapp.data;

/**
 * Created by Admin on 03.02.2016.
 */
public class Review {
    private int movieTmdbId;
    private String author;
    private String content;

    public int getMovieTmdbId() {
        return movieTmdbId;
    }

    public void setMovieTmdbId(int movieTmdbId) {
        this.movieTmdbId = movieTmdbId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

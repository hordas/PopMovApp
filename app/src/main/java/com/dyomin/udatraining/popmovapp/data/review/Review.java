package com.dyomin.udatraining.popmovapp.data.review;

/**
 * Contains values for review entity.
 * --
 * Created by Admin on 03.02.2016.
 */
public class Review {

    private int movieTmdbId;
    private String author;
    private String content;

    @SuppressWarnings("unused")
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

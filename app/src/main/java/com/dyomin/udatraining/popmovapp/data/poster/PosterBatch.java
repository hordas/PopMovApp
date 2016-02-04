package com.dyomin.udatraining.popmovapp.data.poster;

import java.util.List;

/**
 * Contains batch of movieDetailses for current page, a number of current page
 * and total amount of pages.
 * --
 * Created by Admin on 20.07.2015.
 */
public class PosterBatch {

    private List<MovieDetails> movieDetailses;
    private int currentPage;
    private int totalPages;

    public List<MovieDetails> getMovieDetailses() {
        return movieDetailses;
    }

    public void setMovieDetailses(List<MovieDetails> movieDetailses) {
        this.movieDetailses = movieDetailses;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

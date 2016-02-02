package com.dyomin.udatraining.popmovapp.data.poster;

import java.util.List;

/**
 * Contains batch of posters for current page, a number of current page
 * and total amount of pages.
 * --
 * Created by Admin on 20.07.2015.
 */
public class PosterBatch {

    private List<Poster> posters;
    private int currentPage;
    private int totalPages;

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
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

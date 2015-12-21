package com.dyomin.udatraining.popmovapp.data;

/**
 * Class contains info about posters url and movie id.
 * --
 * Created by Admin on 19.07.2015.
 */
public class Poster {

    private String posterUrl;
    private String filmId;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}

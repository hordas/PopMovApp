package com.dyomin.udatraining.popmovapp.provider.review;

import com.dyomin.udatraining.popmovapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Reviews table
 */
public interface ReviewModel extends BaseModel {

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getAuthor();

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getContent();

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();
}

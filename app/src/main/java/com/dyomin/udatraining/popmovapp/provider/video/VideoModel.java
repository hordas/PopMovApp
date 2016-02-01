package com.dyomin.udatraining.popmovapp.provider.video;

import com.dyomin.udatraining.popmovapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Trailers table
 */
public interface VideoModel extends BaseModel {

    /**
     * Get the {@code name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Get the {@code trailer_key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTrailerKey();

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();
}

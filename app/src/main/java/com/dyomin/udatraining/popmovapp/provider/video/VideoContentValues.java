package com.dyomin.udatraining.popmovapp.provider.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyomin.udatraining.popmovapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code video} table.
 */
public class VideoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable VideoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable VideoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public VideoContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(VideoColumns.NAME, value);
        return this;
    }


    public VideoContentValues putTrailerKey(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("trailerKey must not be null");
        mContentValues.put(VideoColumns.TRAILER_KEY, value);
        return this;
    }


    public VideoContentValues putMovieId(int value) {
        mContentValues.put(VideoColumns.MOVIE_ID, value);
        return this;
    }

}

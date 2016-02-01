package com.dyomin.udatraining.popmovapp.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.dyomin.udatraining.popmovapp.BuildConfig;
import com.dyomin.udatraining.popmovapp.provider.movie.MovieColumns;
import com.dyomin.udatraining.popmovapp.provider.review.ReviewColumns;
import com.dyomin.udatraining.popmovapp.provider.video.VideoColumns;

public class PMASQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PMASQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "popmovapp.db";
    private static final int DATABASE_VERSION = 1;
    private static PMASQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PMASQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS "
            + MovieColumns.TABLE_NAME + " ( "
            + MovieColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieColumns.TMDB_ID + " INTEGER NOT NULL, "
            + MovieColumns.ORIGINAL_TITLE + " TEXT NOT NULL, "
            + MovieColumns.MOVIE_POSTER_URI + " TEXT, "
            + MovieColumns.MOVIE_RELEASE_DATE + " TEXT, "
            + MovieColumns.VOTE_AVERAGE + " REAL, "
            + MovieColumns.OVERVIEW + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS "
            + ReviewColumns.TABLE_NAME + " ( "
            + ReviewColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReviewColumns.AUTHOR + " TEXT NOT NULL, "
            + ReviewColumns.CONTENT + " TEXT NOT NULL, "
            + ReviewColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + ReviewColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + " );";

    public static final String SQL_CREATE_TABLE_VIDEO = "CREATE TABLE IF NOT EXISTS "
            + VideoColumns.TABLE_NAME + " ( "
            + VideoColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + VideoColumns.NAME + " TEXT NOT NULL, "
            + VideoColumns.TRAILER_KEY + " TEXT NOT NULL, "
            + VideoColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + VideoColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + " );";

    // @formatter:on

    public static PMASQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PMASQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PMASQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PMASQLiteOpenHelper(context);
    }

    private PMASQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PMASQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PMASQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PMASQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PMASQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PMASQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_REVIEW);
        db.execSQL(SQL_CREATE_TABLE_VIDEO);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}

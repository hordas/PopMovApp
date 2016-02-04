package com.dyomin.udatraining.popmovapp.data.poster;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.dyomin.udatraining.popmovapp.util.Connection;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Adds movieDetailses to MainActivityFragment.
 * --
 * Created by Admin on 19.07.2015.
 */
public class PosterAdapter extends BaseAdapter {

    private List<MovieDetails> movieDetailses;
    private Context context;

    public PosterAdapter(Context context, List<MovieDetails> movieDetailsList) {
        this.context = context;
        this.movieDetailses = movieDetailsList;
    }

    public void updateResults(List<MovieDetails> results) {
        movieDetailses = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieDetailses.size();
    }

    @Override
    public Object getItem(int position) {
        return movieDetailses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(context);
        }
        Picasso.with(context).load(
                Connection.getImageUrl(movieDetailses.get(position).getPosterUrl())
        ).into((ImageView) convertView);
        return convertView;
    }
}

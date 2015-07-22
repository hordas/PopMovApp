package com.dyomin.udatraining.popmovapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Admin on 19.07.2015.
 */
public class PosterAdapter extends BaseAdapter {

    private List<Poster> posters;
    private Context context;

    public PosterAdapter(Context context, List<Poster> posterList) {
        this.context = context;
        this.posters = posterList;
    }

    public void updateResults(List<Poster> results) {
        posters = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

    public String getIdByPosition(int position) {
        return posters.get(position).getFilmId();
    }

    @Override
    public int getCount() {
        return posters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(context).load(
                Connection.getImageUrl(posters.get(position).getPosterUrl())
        ).into(imageView);
        return imageView;
    }
}

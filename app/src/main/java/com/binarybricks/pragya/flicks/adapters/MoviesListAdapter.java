package com.binarybricks.pragya.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarybricks.pragya.flicks.R;
import com.binarybricks.pragya.flicks.models.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by PRAGYA on 10/12/2016.
 */

public class MoviesListAdapter extends ArrayAdapter<Results> {

    public static final String BACKDROP_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w300";

    public MoviesListAdapter(Context context, List<Results> results) {
        super(context, R.layout.movies_item, results);
    }

    // View lookup cache
    static class ViewHolderMovieItem {
        @Nullable @BindView(R.id.ivPoster) ImageView ivPoster;
        @Nullable @BindView(R.id.ivBackdrop) ImageView ivBackdrop;
        @BindView(R.id.tvMovieTitle) TextView tvMovieTitle;
        @BindView(R.id.tvOverview) TextView tvMovieOverview;

        public ViewHolderMovieItem(View view) {
            ButterKnife.bind(this,view);
        }
    }

    static class ViewHolderPopularMovieItem {
        @BindView(R.id.ivBackdrop) ImageView ivBackdrop;
        @Nullable @BindView(R.id.tvPopularMovieTitle) TextView tvPopularMovieTitle;
        @Nullable @BindView(R.id.tvPopularMovieOverview) TextView tvPopularMovieOverview;

        public ViewHolderPopularMovieItem(View view) {
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (Double.parseDouble(getItem(position).getVote_average()) >= 5) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ButterKnife.bind(this,convertView);
        // Get the data item for this position
        Results results = getItem(position);

        int viewType = this.getItemViewType(position);

        //Check if the viewType and inflate the view accordingly
        if (viewType == 1) {

            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolderPopularMovieItem viewHolderPopular;
            if (convertView == null) {
                // If there's no view to re-use, inflate a brand new view for row
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.movies_popular_item, null);
                viewHolderPopular = new ViewHolderPopularMovieItem(convertView);
                convertView.setTag(viewHolderPopular);

            } else {
                // View is being recycled, retrieve the viewHolder object from tag
                viewHolderPopular = (ViewHolderPopularMovieItem) convertView.getTag();
            }

            // Populate the data into the template view using the data object
                Picasso.with(getContext()).load(BACKDROP_IMAGE_URL + results.getBackdrop_path()).transform(new RoundedCornersTransformation(10,10)).
                        placeholder(R.drawable.movie_image_placeholder).error(R.drawable.error_placeholder).into(viewHolderPopular.ivBackdrop);

            if (viewHolderPopular.tvPopularMovieTitle != null){
                viewHolderPopular.tvPopularMovieTitle.setText(results.getTitle());
                viewHolderPopular.tvPopularMovieOverview.setText(results.getOverview());
            }
            // Return the completed view to render on screen
            return convertView;
        } else {
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolderMovieItem viewHolder2;
            if (convertView == null) {
                // If there's no view to re-use, inflate a brand new view for row
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.movies_item, null);

                viewHolder2 = new ViewHolderMovieItem(convertView);
                convertView.setTag(viewHolder2);
            } else {
                // View is being recycled, retrieve the viewHolder object from tag
                viewHolder2 = (ViewHolderMovieItem) convertView.getTag();
            }

            // Populate the data into the template view using the data object and checking the orientation
            int orientation = convertView.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(POSTER_IMAGE_URL + results.getPoster_path()).transform(new RoundedCornersTransformation(10,10)).
                        placeholder(R.drawable.movie_image_placeholder).error(R.drawable.error_placeholder).into(viewHolder2.ivPoster);
            }else{
                Picasso.with(getContext()).load(BACKDROP_IMAGE_URL + results.getBackdrop_path()).transform(new RoundedCornersTransformation(10,10)).
                        placeholder(R.drawable.movie_image_placeholder).error(R.drawable.error_placeholder).into(viewHolder2.ivPoster);
            }
            viewHolder2.tvMovieTitle.setText(results.getTitle());
            viewHolder2.tvMovieOverview.setText(results.getOverview());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}

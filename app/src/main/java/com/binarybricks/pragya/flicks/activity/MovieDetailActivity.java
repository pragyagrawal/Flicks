package com.binarybricks.pragya.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.binarybricks.pragya.flicks.R;
import com.binarybricks.pragya.flicks.adapters.MoviesListAdapter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivMovieBackdrop)
    ImageView ivBackdrop;
    @BindView(R.id.ivMoviePoster)
    ImageView ivMoviePoster;
    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;
    @BindView(R.id.rbMovieRating)
    RatingBar rbMovieRating;
    @BindView(R.id.tvOverview)
    TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(MovieDetailActivity.this);
        Bundle bundle = getIntent().getExtras();

        final String movieId = bundle.getString(MoviesActivity.MOVIE_ID);
        String backdrop_path = bundle.getString(MoviesActivity.BACKDROP_PATH);
        Picasso.with(MovieDetailActivity.this).load(MoviesListAdapter.BACKDROP_IMAGE_URL+backdrop_path).transform(new RoundedCornersTransformation(10,10)).
                placeholder(R.drawable.movie_image_placeholder).error(R.drawable.error_placeholder).into(ivBackdrop);

        String poster_path = bundle.getString(MoviesActivity.POSTER_PATH);
        Picasso.with(MovieDetailActivity.this).load(MoviesListAdapter.POSTER_IMAGE_URL+poster_path).transform(new RoundedCornersTransformation(10,10)).
                placeholder(R.drawable.movie_image_placeholder).error(R.drawable.error_placeholder).into(ivMoviePoster);

        tvMovieTitle.setText(bundle.getString(MoviesActivity.MOVIE_TITLE));

        String releaseDate = bundle.getString(MoviesActivity.MOVIE_RELEASE_DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd,yyyy");
        tvReleaseDate.setText(newFormat.format(date));

        Float movieRating = Float.parseFloat(bundle.getString(MoviesActivity.MOVIE_RATING));
        rbMovieRating.setRating(movieRating);

        tvOverview.setText(bundle.getString(MoviesActivity.MOVIE_OVERVIEW));

        ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, QuickPlayActivity.class);
                intent.putExtra(MoviesActivity.MOVIE_ID, movieId);
                startActivity(intent);
            }
        });
    }
}

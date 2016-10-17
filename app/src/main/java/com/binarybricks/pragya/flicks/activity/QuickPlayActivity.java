package com.binarybricks.pragya.flicks.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.binarybricks.pragya.flicks.R;
import com.binarybricks.pragya.flicks.models.MovieTrailors;
import com.binarybricks.pragya.flicks.models.Youtube;
import com.binarybricks.pragya.flicks.network.MoviesAPI;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuickPlayActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyChtV9C8PIw9_yx8_GC5vS46J6YC7WiYmM";
    @BindView(R.id.player) YouTubePlayerView youTubePlayerView;

    private YouTubePlayer videoPlayer;
    private String movieID;
    public static final String MOVIE_ID = "movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        ButterKnife.bind(this);

        movieID = getIntent().getStringExtra(MOVIE_ID);

        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                videoPlayer = youTubePlayer;
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        if (!TextUtils.isEmpty(movieID)) {
            MoviesAPI.getMovieTrailers(new Callback<MovieTrailors>() {
                @Override
                public void onResponse(Call<MovieTrailors> call, Response<MovieTrailors> response) {
                    if(response.isSuccessful()) {
                        MovieTrailors movieTrailers = response.body();
                        List<Youtube> trailersList = movieTrailers.getYoutube();
                        if (!trailersList.isEmpty() && videoPlayer != null) {
                            videoPlayer.loadVideo(trailersList.get(0).getSource());
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieTrailors> call, Throwable t) {
                    Toast.makeText(QuickPlayActivity.this, R.string.no_internet_error, Toast.LENGTH_SHORT).show();
                }
            },movieID);
        }
    }
}

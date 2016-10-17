package com.binarybricks.pragya.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.binarybricks.pragya.flicks.R;
import com.binarybricks.pragya.flicks.adapters.MoviesListAdapter;
import com.binarybricks.pragya.flicks.models.MoviesData;
import com.binarybricks.pragya.flicks.models.Results;
import com.binarybricks.pragya.flicks.network.MoviesAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String POSTER_PATH = "poster_path";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_RATING = "movie_rating";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_OVERVIEW = "movie_overview";
    public static final String MOVIE_ID = "movie_id";
    @BindView(R.id.lvMovies)
    ListView lvMovies;

    @BindView(R.id.swipeRefreshContainer)
    SwipeRefreshLayout swipeRefreshContainer;

    private MoviesListAdapter moviesListAdapter;
    private List<Results> moviesResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ButterKnife.bind(MoviesActivity.this);

        moviesResultList = new ArrayList<>();
        moviesListAdapter = new MoviesListAdapter(MoviesActivity.this, moviesResultList);

        lvMovies.setAdapter(moviesListAdapter);

        //Add clickListner to the list view to play movie trailer for
        // popular movies or open detail activity for non popular movies
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Results movieResults = moviesResultList.get(position);
                if (Double.parseDouble(movieResults.getVote_average()) >= 5) {
                    Intent intent = new Intent(MoviesActivity.this, QuickPlayActivity.class);
                    intent.putExtra(QuickPlayActivity.MOVIE_ID, movieResults.getId());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                    intent.putExtra(MOVIE_ID, movieResults.getId());
                    intent.putExtra(BACKDROP_PATH, movieResults.getBackdrop_path());
                    intent.putExtra(POSTER_PATH, movieResults.getPoster_path());
                    intent.putExtra(MOVIE_TITLE, movieResults.getTitle());
                    intent.putExtra(MOVIE_RATING, movieResults.getVote_average());
                    intent.putExtra(MOVIE_RELEASE_DATE, movieResults.getRelease_date());
                    intent.putExtra(MOVIE_OVERVIEW, movieResults.getOverview());
                    startActivity(intent);
                }
            }
        });

        // Setup refresh listener which triggers new data loading
        swipeRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchAndDisplayMovieData();
            }
        });
        fetchAndDisplayMovieData();
    }

    private void fetchAndDisplayMovieData() {
        lvMovies.setVisibility(View.INVISIBLE);
        MoviesAPI.getMovies(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {
                if (response.isSuccessful()) {
                    MoviesData moviesData = response.body();
                    moviesResultList = moviesData.getResults();
                    moviesListAdapter.addAll(moviesResultList);
                    moviesListAdapter.notifyDataSetChanged();
                    lvMovies.setVisibility(View.VISIBLE);
                    swipeRefreshContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, R.string.no_internet_error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.binarybricks.pragya.flicks.network;

import com.binarybricks.pragya.flicks.interfaces.MoviesAPIInterface;
import com.binarybricks.pragya.flicks.models.MovieTrailors;
import com.binarybricks.pragya.flicks.models.MoviesData;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by PRAGYA on 10/16/2016.
 * DealsWebService provides implementation of Movies API
 */

public class MoviesAPI {

    public static void getMovies(Callback<MoviesData> callback) {
        MoviesAPIInterface moviesAPIInterface = RetroClient.getRetrofit().create(MoviesAPIInterface.class);
        Call<MoviesData> call = moviesAPIInterface.getMoviesFromServer(RetroClient.API_KEY);
        call.enqueue(callback);
    }

    public static void getMovieTrailers(Callback<MovieTrailors> callback,String movieId) {
        MoviesAPIInterface moviesAPIInterface = RetroClient.getRetrofit().create(MoviesAPIInterface.class);
        Call<MovieTrailors> call = moviesAPIInterface.getMovieTrailers(movieId,RetroClient.API_KEY);
        call.enqueue(callback);
    }
}

package com.binarybricks.pragya.flicks.interfaces;

import com.binarybricks.pragya.flicks.models.MovieTrailors;
import com.binarybricks.pragya.flicks.models.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PRAGYA on 10/16/2016.
 */

public interface MoviesAPIInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("now_playing")
    Call<MoviesData> getMoviesFromServer(@Query("api_key") String apiKey);

    @GET("{movieId}/trailers")
    Call<MovieTrailors> getMovieTrailers(@Path("movieId") String movieId,@Query("api_key") String apiKey);
}

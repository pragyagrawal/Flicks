package com.binarybricks.pragya.flicks.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PRAGYA on 10/6/2016.
 * RetroClient provides the implementation of Retrofit
 *
 */

public class RetroClient {

    // Trailing slash is needed
    private static final String MOVIES_URL = "https://api.themoviedb.org/3/movie/";
    public static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(httpLoggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIES_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }
        return retrofit;
    }
}

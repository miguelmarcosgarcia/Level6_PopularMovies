package com.example.level6_popularmovies.services

import com.example.level6_popularmovies.models.MoviePage
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface MovieApiService {

    //GETTER - RETRIEVE A RANDOM Nº TRIVIA
    @GET("/3/discover/movie?language=en-US&sort_by=popularity" +
            ".desc&include_adult=false&include_video=false&page=1")
    fun getListOfMovies(
        @Query("api_key") key: String,
        @Query("primary_release_year") year:String
    ): Call<MoviePage>
}
package com.example.level6_popularmovies.services

import com.example.level6_popularmovies.models.MoviePage
import retrofit2.Call

class MovieRepository {
    private val movieApi: MovieApiService = MoviesApi.createApi()

    fun getMovies(key: String, year: String) : Call<MoviePage> {
        return movieApi.getListOfMovies(key,year)
    }
}
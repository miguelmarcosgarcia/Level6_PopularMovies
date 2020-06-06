package com.example.level6_popularmovies.activities

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.level6_popularmovies.models.MoviePage
import com.example.level6_popularmovies.services.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    val movies = MutableLiveData<MoviePage>()
    val error = MutableLiveData<String>()
    private val movieRepository = MovieRepository()

    fun getMovies(key: String, year: String){
        movieRepository.getMovies(key, year).enqueue(object: Callback<MoviePage>{
            override fun onResponse(call: Call<MoviePage>, response: Response<MoviePage>) {
                if(response.isSuccessful){
                    movies.value=response.body()
                }else{
                    error.value =  "Sorry, an error ocurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<MoviePage>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}
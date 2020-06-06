package com.example.level6_popularmovies.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.level6_popularmovies.R
import com.example.level6_popularmovies.adapters.MovieAdapter
import com.example.level6_popularmovies.models.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val apiKey = "334b5b4536f9132001cee88fe470cd8e"
    }
    private lateinit var viewModel: MainActivityViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) { movie:Movie -> startMovieActivity(movie) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }
    private fun startMovieActivity(movie: Movie){
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.MOVIE_EXTRA, movie)
        startActivity(intent)
    }
    private fun initViews(){
        rvMovies.layoutManager = GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false)
        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener{
            viewModel.getMovies(apiKey, etYear.text.toString())
        }
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it.movies)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this,
        Observer { Toast.makeText(this,
        it, Toast.LENGTH_LONG).show() })
    }
}
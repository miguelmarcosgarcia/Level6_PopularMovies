package com.example.level6_popularmovies.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.level6_popularmovies.R
import com.example.level6_popularmovies.models.Movie
import kotlinx.android.synthetic.main.content_movie.*

class MovieActivity: AppCompatActivity() {

    companion object{
        const val MOVIE_EXTRA = "MOVIE_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initViews(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA)

        if(movie != null){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            tvReleaseDate.text = movie.releaseDate
            tvRating.text = movie.rating.toString()

            Glide.with(this).load(movie.getBackDropUrl()).into(ivBackdrop)
            Glide.with(this).load(movie.getPosterUrl()).into(ivPoster)
        }
    }
}
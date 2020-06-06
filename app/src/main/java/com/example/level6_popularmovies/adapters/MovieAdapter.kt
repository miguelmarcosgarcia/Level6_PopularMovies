package com.example.level6_popularmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.level6_popularmovies.R
import com.example.level6_popularmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private  val movies: List<Movie>, val clickListener: (Movie) -> Unit):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, pos: Int){
            itemView.tvNumber.text = "$pos."
            Glide.with(context).load(movie.getPosterUrl()).into(itemView.ivImage)
            itemView.setOnClickListener{clickListener(movie)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], position+1)
    }
}
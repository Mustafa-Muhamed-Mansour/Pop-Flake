package com.pop_flake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pop_flake.R
import com.pop_flake.databinding.ItemMovieBinding
import com.pop_flake.interfaces.ClickedMovie
import com.pop_flake.model.MovieModel

class HomeAdapter(
    private val clickedMovie: ClickedMovie
) : Adapter<HomeAdapter.HomeViewHolder>() {

    private val itemCallback = object : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.country == newItem.country
                    && oldItem.end_date == newItem.end_date
                    && oldItem.id == newItem.id
                    && oldItem.start_date == newItem.start_date
                    && oldItem.image_thumbnail_path == newItem.image_thumbnail_path
                    && oldItem.name == newItem.name
                    && oldItem.network == newItem.network
                    && oldItem.permalink == newItem.permalink
                    && oldItem.status == newItem.status
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, itemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        differ.currentList[position].apply {
            holder.bind.imgItemMovie.load(this.image_thumbnail_path) {
                this.crossfade(1500)
                this.placeholder(R.drawable.no_photo)
                this.error(R.drawable.watching_movie)
                this.transformations(RoundedCornersTransformation(15f))
            }

            holder.bind.txtNameItemMovie.text = this.name
            holder.bind.txtCountryItemMovie.text = this.country

            holder.itemView.setOnClickListener {
                clickedMovie.clickedMovieToDetail(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class HomeViewHolder(binding: ItemMovieBinding) : ViewHolder(binding.root) {
        val bind = binding
    }
}
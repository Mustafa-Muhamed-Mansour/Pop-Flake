package com.pop_flake.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.pop_flake.R
import com.pop_flake.databinding.ItemSliderMovieBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderMovieAdapter(private val pictures: List<String>) :
    SliderViewAdapter<SliderMovieAdapter.SliderMovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup?): SliderMovieViewHolder {
        return SliderMovieViewHolder(ItemSliderMovieBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: SliderMovieViewHolder?, position: Int) {
        pictures[position].apply {
            if (this.isEmpty()) {
                viewHolder?.bind?.imgItemMovie?.load(R.drawable.no_photo) {
                    this.crossfade(2000)
                }
            } else {
                viewHolder?.bind?.imgItemMovie?.load(this)
            }
        }
    }

    override fun getCount(): Int {
        return pictures.size
    }

    class SliderMovieViewHolder(binding: ItemSliderMovieBinding) : ViewHolder(binding.root) {
        val bind = binding
    }

}
package com.mobilebank.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.mobilebank.R
import com.mobilebank.data.model.CardUiModel

class MovieAdapter(private val movies : ArrayList<CardUiModel>) : CardSliderAdapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return MovieViewHolder(view)
    }
    
    override fun bindVH(holder: MovieViewHolder, position: Int) {
      //TODO bind item object with item layout view
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
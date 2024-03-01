package com.mobilebank.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilebank.data.CardsViewPagerItem
import com.mobilebank.databinding.ListItemCardBinding

class CardsViewPagerAdapter(var items: List<CardsViewPagerItem>) :
    RecyclerView.Adapter<CardsViewPagerAdapter.ViewPagerViewHolder>() {


    class ViewPagerViewHolder(val binding: ListItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardsViewPagerItem) {
//            binding.imgOnboarding.load(item.image)
//            binding.txtTitle.text = itemView.context.getString(item.title)
//            binding.txtSubtitle.text = itemView.context.getString(item.subTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCardBinding.inflate(inflater, parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
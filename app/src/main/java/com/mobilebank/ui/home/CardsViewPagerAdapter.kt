package com.mobilebank.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.mobilebank.data.model.CardUiModel
import com.mobilebank.data.model.CardsViewPagerItem
import com.mobilebank.databinding.LayoutHomeCardItemBinding
import com.mobilebank.utils.asDp

class CardsViewPagerAdapter(var items: List<CardUiModel>) :
    RecyclerView.Adapter<CardsViewPagerAdapter.CardsViewPagerViewHolder>() {


    inner class CardsViewPagerViewHolder(val binding: LayoutHomeCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardUiModel) {
            if (itemCount > 1) {
                binding.root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    when (layoutPosition) {
                        0 -> {
                            marginStart = 0
                            marginEnd = 24.asDp
                        }

                        itemCount - 1 -> {
                            marginStart = 24.asDp
                            marginEnd = 0
                        }

                        else -> {
                            marginStart = 24.asDp
                            marginEnd = 24.asDp
                        }
                    }
                }
                binding.amount.text = item.amount
                binding.cardNumber.text = item.cardNumber.toString()
//            binding.txtSubtitle.text = itemView.context.getString(item.subTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutHomeCardItemBinding.inflate(inflater, parent, false)
        return CardsViewPagerViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CardsViewPagerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
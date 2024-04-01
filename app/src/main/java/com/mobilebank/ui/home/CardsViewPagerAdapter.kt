package com.mobilebank.ui.home

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.mobilebank.data.model.CardUiModel
import com.mobilebank.data.model.CardsViewPagerItem
import com.mobilebank.databinding.LayoutHomeCardItemBinding
import com.mobilebank.utils.asDp
import com.mobilebank.utils.decreaseTextSize
import com.mobilebank.utils.increaseTextSize
import com.mobilebank.utils.load

class CardsViewPagerAdapter(var items: List<CardUiModel>, var context: Context) :
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
                binding.name.text = item.name
                binding.amount.text = item.amount
                binding.cardImage.load(item.backgroundImage)
                binding.cardNumber.text = item.cardNumber
                binding.expireDate.text = item.expiryDate
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

//    fun updateIncreaseUi(view: View?, action: () -> Unit = {}) {
//        if (view is TextView) {
//            val metrics = context.resources.displayMetrics
//            val textsize: Float = view.textSize / metrics.density
//            val size = increaseTextSize(textsize)
//            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
//        }
//
//        if (view is ViewGroup) {
//            for (i in 0 until view.childCount) {
//                val innerView = view.getChildAt(i)
//                updateIncreaseUi(innerView)
//            }
//        }
//    }
//
//    fun updateDecreaseUi(view: View?, action: () -> Unit = {}) {
//        if (view is TextView) {
//            val metrics = context.resources.displayMetrics
//            val textsize: Float = view.textSize / metrics.density
//            val size = decreaseTextSize(textsize)
//            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
//        }
//
//        if (view is ViewGroup) {
//            for (i in 0 until view.childCount) {
//                val innerView = view.getChildAt(i)
//                updateDecreaseUi(innerView)
//            }
//        }
//    }

}
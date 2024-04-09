package com.mobilebank.ui.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobilebank.R
import com.mobilebank.data.model.TransactionsUiModel
import java.util.Calendar

class TransactionsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val transactionList = arrayListOf<TransactionsUiModel>()
//    private lateinit var onClickListener: ListItemOnClickListener<TransactionsUiModel>

    private lateinit var context: Context

    var currentPage: Int = 1
    var totalPage: Int = 0
    var canLoad: Boolean = true

    fun addTransactions(transactionList: List<TransactionsUiModel>) {

        if (transactionList.isEmpty() || (this.transactionList.isNotEmpty() && transactionList.last() == this.transactionList.last())) {
            canLoad = false
        } else {
            currentPage += 1
            this.transactionList.addAll(transactionList)
        }
        notifyDataSetChanged()
    }

    fun setTransactions(transactionList: List<TransactionsUiModel>) {
        this.transactionList.clear()
        this.canLoad = true
        this.currentPage = PAGE_1
        this.transactionList.addAll(transactionList)
        notifyDataSetChanged()
    }

//    fun setOnItemClickListener(listener: ListItemOnClickListener<TransactionsUiModel>) {
//        this.onClickListener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_transaction_list_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TransactionViewHolder) {
            val transaction = transactionList[position]

            holder.transactionName.text = transaction.title
            holder.currency.text = "USD"
            holder.date.text = transaction.subtitle

            val amount = transaction.amount ?: AMOUNT_ZERO

            try {
//                val formatter = uiActionHandler.getAmountFormatter()
//                val totalString = formatter?.format(amount)

//                holder.transactionAmount.text = if (amount > 0) "+ $totalString" else totalString
                holder.transactionIcon.setImageResource(transaction.icon)

                if (amount > 0) {
                    holder.transactionAmount.text = "$PLUS${transaction.endLabel}"
                    holder.transactionAmount.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBlack
                        )
                    )
                    holder.currency.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBlack
                        )
                    )
                } else {
                    holder.transactionAmount.setTextColor(
                        Color.RED
                    )
                    holder.currency.setTextColor(

                        Color.RED

                    )
                    holder.transactionAmount.text = transaction.endLabel

                }


            } catch (e: Exception) {
                //
            }
        }
    }



    private fun checkDateEqual(transactionDate: Calendar, offset: Int): Boolean {
        val now = Calendar.getInstance()
        now.add(Calendar.DATE, offset)
        return transactionDate.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
                && transactionDate.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && transactionDate.get(Calendar.YEAR) == now.get(Calendar.YEAR)
    }

    override fun getItemCount() = if (transactionList.isEmpty()) transactionList.size else 6

//    override fun getItemViewType(position: Int) = transactionList[position].viewType

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionIcon: ImageView = view.findViewById(R.id.transactionIcon)
        val transactionName: TextView = view.findViewById(R.id.transactionName)
        val transactionAmount: TextView = view.findViewById(R.id.transactionAmount)
        val currency: TextView = view.findViewById(R.id.currency)
        val date: TextView = view.findViewById(R.id.date)
//        val ips: TextView = view.findViewById(R.id.ips)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout)
    }

    class DefaultViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        const val IPS = "IPS"
        const val NORMAL = "NORMAL"
        const val OFFSET_LESS = -1
        const val OFFSET_EQUAL = 0
        const val PAGE_1 = 1
        const val PLUS = "+"
        const val AMOUNT_ZERO = 0.0
        const val TRANSACTION_FILTER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ"
        const val TRANSACTION_FILTER_DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss"
    }
}
package cz.vojtechvosmik.cashcog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.model.Transaction
import cz.vojtechvosmik.cashcog.model.TransactionType
import cz.vojtechvosmik.cashcog.util.DateUtils
import cz.vojtechvosmik.cashcog.util.StorageUtils
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter(private val context: Context, private val items: List<Transaction>) : RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TransactionViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(context, itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: TransactionViewHolder, position: Int) {
        viewHolder.setupViews(items[position])
    }
}

class TransactionViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setupViews(item: Transaction) {
        itemView.txt_note.text = item.note
        itemView.txt_date.text = DateUtils.changeDateFormat(item.date, "dd.MM.yyyy")
        if (item.type == TransactionType.PLUS) {
            itemView.txt_amount.text = ("+ " + item.amount.toString())
            itemView.txt_amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else if (item.type == TransactionType.MINUS) {
            itemView.txt_amount.text = ("- " + item.amount.toString())
            itemView.txt_amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        itemView.txt_amount.text = (itemView.txt_amount.text.toString()  + " " + StorageUtils.getValue(context, "currency", "CZK"))
    }
}
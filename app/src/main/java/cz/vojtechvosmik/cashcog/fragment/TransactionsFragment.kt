package cz.vojtechvosmik.cashcog.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.activity.NewTransactionActivity
import cz.vojtechvosmik.cashcog.adapters.TransactionAdapter
import cz.vojtechvosmik.cashcog.db.AppDatabase
import cz.vojtechvosmik.cashcog.model.Transaction
import cz.vojtechvosmik.cashcog.model.TransactionType
import cz.vojtechvosmik.cashcog.util.FragmentUtils
import cz.vojtechvosmik.cashcog.util.StorageUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.android.synthetic.main.toolbar.view.*

class TransactionsFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_transaction
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        initContent()
    }

    private fun initViews() {
        recycler_transactions.layoutManager = LinearLayoutManager(context)
        fab_add.setOnClickListener { startActivity(Intent(context, NewTransactionActivity::class.java)) }
        fab_graph.setOnClickListener { FragmentUtils.changeFragment(GraphFragment(), true, false) }
        fab_currency.setOnClickListener { FragmentUtils.changeFragment(CurrencyFragment(), true, false) }
    }

    private fun initContent() {
        if (context != null) {
            val transactions = AppDatabase.getAppDatabase(context!!)?.transactionDao()?.getEvents()
            if (!transactions.isNullOrEmpty()) {
                recycler_transactions.visibility = View.VISIBLE
                txt_no_transactions.visibility = View.GONE
                recycler_transactions.adapter = TransactionAdapter(context!!, transactions.reversed())
            }else {
                recycler_transactions.visibility = View.GONE
                txt_no_transactions.visibility = View.VISIBLE
            }
            summary(transactions)
        }
    }

    private fun summary(transactions: List<Transaction>?) {
        var summary = 0
        var summaryPlus = 0
        var summaryMinus = 0
        transactions?.forEach {
            if (it.type == TransactionType.PLUS) {
                summary += it.amount
                summaryPlus += it.amount
            }else if (it.type == TransactionType.MINUS) {
                summary -= it.amount
                summaryMinus -= it.amount
            }
        }
        txt_summary.text = summary.toString()
        if (summary > 0) {
            txt_summary.text = ("+ " + txt_summary.text)
            txt_summary.setTextColor(ContextCompat.getColor(context!!, R.color.green))
        }else if (summary < 0) {
            txt_summary.text = ("- " + (txt_summary.text!!.toString().toInt() * -1).toString())
            txt_summary.setTextColor(ContextCompat.getColor(context!!, R.color.red))
        }
        txt_summary.text = (txt_summary.text.toString() + " " + StorageUtils.getValue(context!!, "currency", "CZK"))
        txt_plus_summary.text = (("+ $summaryPlus") + " " + StorageUtils.getValue(context!!, "currency", "CZK"))
        txt_minus_summary.text = (("- " + (summaryMinus * -1).toString()) + " " + StorageUtils.getValue(context!!, "currency", "CZK"))
    }
}
package cz.vojtechvosmik.cashcog.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.adapters.TransactionAdapter
import cz.vojtechvosmik.cashcog.db.AppDatabase
import cz.vojtechvosmik.cashcog.model.Transaction
import cz.vojtechvosmik.cashcog.model.TransactionType
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.util.*

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
    }

    private fun initContent() {
        if (context != null) {
            val transactions = AppDatabase.getAppDatabase(context!!)?.transactionDao()?.getEvents()
            if (!transactions.isNullOrEmpty()) {
                recycler_transactions.visibility = View.VISIBLE
                txt_no_transactions.visibility = View.GONE
                recycler_transactions.adapter = TransactionAdapter(context!!, transactions)
            }else {
                recycler_transactions.visibility = View.GONE
                txt_no_transactions.visibility = View.VISIBLE
            }
        }
    }
}
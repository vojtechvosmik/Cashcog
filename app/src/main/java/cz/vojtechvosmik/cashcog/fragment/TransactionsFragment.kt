package cz.vojtechvosmik.cashcog.fragment

import android.os.Bundle
import android.view.View
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.db.AppDatabase

class TransactionsFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_transaction
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initContent()
    }

    private fun initViews() {

    }

    private fun initContent() {
        val transactions = AppDatabase.getAppDatabase(context!!)?.transactionDao()?.getEvents()
    }
}
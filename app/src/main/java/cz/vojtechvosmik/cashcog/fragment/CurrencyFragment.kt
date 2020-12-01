package cz.vojtechvosmik.cashcog.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.util.FragmentUtils
import cz.vojtechvosmik.cashcog.util.StorageUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_currency.*
import kotlinx.android.synthetic.main.toolbar.view.*

class CurrencyFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_currency
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        activity?.include_toolbar?.img_back?.visibility = View.VISIBLE
        activity?.include_toolbar?.img_back?.setOnClickListener {
            activity?.include_toolbar?.img_back?.visibility = View.INVISIBLE
            FragmentUtils.fragmentManager?.popBackStack()
        }
        edittxt_currency.setText(StorageUtils.getValue(context!!, "currency", "CZK"))
        fab_submit.setOnClickListener {
            StorageUtils.saveValue(context!!, "currency", edittxt_currency.text.toString())
            activity?.include_toolbar?.img_back?.visibility = View.INVISIBLE
            FragmentUtils.fragmentManager?.popBackStack()
            Toast.makeText(context!!, getString(R.string.currency_changed), Toast.LENGTH_SHORT).show()
        }
    }
}
package cz.vojtechvosmik.cashcog.fragment

import android.os.Bundle
import android.view.View
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.db.AppDatabase
import cz.vojtechvosmik.cashcog.model.TransactionType
import cz.vojtechvosmik.cashcog.util.DateUtils
import cz.vojtechvosmik.cashcog.util.FragmentUtils
import kotlinx.android.synthetic.main.fragment_graph.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.eazegraph.lib.models.BarModel

class GraphFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_graph
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initContent()
    }

    private fun initViews() {
        activity?.include_toolbar?.img_back?.visibility = View.VISIBLE
        activity?.include_toolbar?.img_back?.setOnClickListener {
            activity?.include_toolbar?.img_back?.visibility = View.INVISIBLE
            FragmentUtils.fragmentManager?.popBackStack()
        }
    }

    private fun initContent() {
        val transactions = AppDatabase.getAppDatabase(context!!)?.transactionDao()?.getEvents()
        var sum = 0f
        var color = -0xedcbaa
        var lastDate: String? = null
        transactions?.forEach {
            if (lastDate == null) {
                lastDate = DateUtils.changeDateFormat(it.date, "dd-MM-yyyy")
            }
            if (lastDate != DateUtils.changeDateFormat(it.date, "dd-MM-yyyy")) {
                mBarChart.addBar(BarModel(lastDate, sum, color))
                sum = 0f
                color = -0xedcbaa
                lastDate = DateUtils.changeDateFormat(it.date, "dd-MM-yyyy")
            }
            if (it.type == TransactionType.PLUS) {
                sum += it.amount.toFloat()
            }else if (it.type == TransactionType.MINUS) {
                sum -= it.amount.toFloat()
            }
            if (sum < 0f) {
                sum *= -1
                color = -0x78c0aa
            }
        }
        mBarChart.addBar(BarModel(lastDate, sum, color))
        mBarChart.startAnimation()
    }
}
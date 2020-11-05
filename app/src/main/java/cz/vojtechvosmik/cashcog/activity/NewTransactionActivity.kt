package cz.vojtechvosmik.cashcog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.db.AppDatabase
import cz.vojtechvosmik.cashcog.model.Transaction
import cz.vojtechvosmik.cashcog.model.TransactionType
import kotlinx.android.synthetic.main.activity_new_transaction.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.*

class NewTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)
        initViews()
    }

    private fun initViews() {
        include_toolbar.img_back.visibility = View.VISIBLE
        include_toolbar.img_back.setOnClickListener { finish() }
        fab_add.setOnClickListener { add() }
    }

    private fun add() {
        if (edittxt_note.text.toString().isEmpty() || edittxt_amount.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.error_add_transaction_inputs), Toast.LENGTH_SHORT).show()
            return
        }
        AppDatabase.getAppDatabase(this)?.transactionDao()?.insertEvent(Transaction(
            note = edittxt_note.text.toString(),
            amount = edittxt_amount.text.toString().toInt(),
            type = if (switch_type.isChecked) { TransactionType.MINUS } else { TransactionType.PLUS },
            date = Date()
        ))
        Toast.makeText(this, getString(R.string.transaction_added), Toast.LENGTH_SHORT).show()
        finish()
    }
}
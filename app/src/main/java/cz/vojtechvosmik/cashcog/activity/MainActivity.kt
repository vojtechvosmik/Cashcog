package cz.vojtechvosmik.cashcog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cz.vojtechvosmik.cashcog.R
import cz.vojtechvosmik.cashcog.fragment.TransactionsFragment
import cz.vojtechvosmik.cashcog.util.FragmentUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentUtils.fragmentManager = supportFragmentManager
        FragmentUtils.changeFragment(TransactionsFragment(), false, removePrevious = false)
    }
}
package cz.vojtechvosmik.cashcog.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cz.vojtechvosmik.cashcog.R

object FragmentUtils {

    var fragmentManager: FragmentManager? = null

    fun changeFragment(fragment: Fragment, backStack: Boolean, removePrevious: Boolean) {
        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            if (removePrevious) {
                val topFragment = fragmentManager.findFragmentById(R.id.fragment_container)
                if (topFragment != null)
                    fragmentTransaction.remove(topFragment)
            }
            fragmentTransaction.add(R.id.fragment_container, fragment)
            if (backStack)
                fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
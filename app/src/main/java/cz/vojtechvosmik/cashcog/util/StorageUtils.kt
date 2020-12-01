package cz.vojtechvosmik.cashcog.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object StorageUtils {

    private const val TAG: String = "StorageUtils"

    fun saveValue(context: Context, key: String, value: String) {
        Thread(Runnable {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.packageName, 0)
            val preferencesEditor = sharedPreferences.edit()
            preferencesEditor.putString(key, value)
            preferencesEditor.apply()
            Log.d(TAG, "SAVE VALUE $key -> $value")
        }).start()
    }

    fun getValue(context: Context, key: String, defaultValue: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.packageName, 0)
        Log.d(TAG, "GET VALUE " + key + " -> " + sharedPreferences.getString(key, ""))
        return sharedPreferences.getString(key, defaultValue)
    }

    fun deleteValue(context: Context, key: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.packageName, 0)
        sharedPreferences.edit().remove(key).apply()
    }
}
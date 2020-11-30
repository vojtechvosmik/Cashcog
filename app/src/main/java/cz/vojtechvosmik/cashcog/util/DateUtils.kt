package cz.vojtechvosmik.cashcog.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateFromString(dateString: String, dateFormat: String) : Date? {
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            simpleDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun changeDateFormat(date: Date, format: String): String? {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return try {
            simpleDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}
package cz.vojtechvosmik.cashcog.db

import androidx.room.TypeConverter
import cz.vojtechvosmik.cashcog.model.TransactionType
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun typeToString(type: TransactionType): String? {
        return type.toString()
    }

    @TypeConverter
    fun typeToString(string: String): TransactionType? {
        return when (string) {
            TransactionType.PLUS.toString() -> {
                TransactionType.PLUS
            }
            TransactionType.MINUS.toString() -> {
                TransactionType.MINUS
            }
            else -> {
                null
            }
        }
    }
}
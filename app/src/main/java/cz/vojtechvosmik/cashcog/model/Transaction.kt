package cz.vojtechvosmik.cashcog.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction")
data class Transaction(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        var note: String,
        var value: Double,
        var date: Date
)
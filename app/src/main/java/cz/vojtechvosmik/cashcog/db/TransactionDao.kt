package cz.vojtechvosmik.cashcog.db

import androidx.room.*
import cz.vojtechvosmik.cashcog.model.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getEvents(): List<Transaction>

    @Query("SELECT * FROM `transaction` WHERE id = :id")
    fun getEventsWithId(id: Int): List<Transaction>

    @Insert
    fun insertEvent(event: Transaction)

    @Update
    fun updateEvent(event: Transaction)

    @Delete
    fun deleteEvent(event: Transaction)
}
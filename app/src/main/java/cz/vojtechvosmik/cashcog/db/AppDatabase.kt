package cz.vojtechvosmik.cashcog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.vojtechvosmik.cashcog.model.Transaction

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (appDatabase == null) {
                synchronized(AppDatabase::class) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "db"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }

    abstract fun transactionDao(): TransactionDao
}
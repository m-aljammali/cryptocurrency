package com.jammali.cryptocurrency.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammali.cryptocurrency.data.local.database.DB.DATABASE_NAME
import com.jammali.cryptocurrency.data.local.database.DB.DATABASE_VERSION

@Database(entities = [CoinsListEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class CoinsDatabase : RoomDatabase() {

    abstract fun coinsListDao(): CoinsListDao

    companion object {

        @Volatile
        private var Instance: CoinsDatabase? = null


        fun getDatabase(context: Context): CoinsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CoinsDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }

            }
        }
    }
}
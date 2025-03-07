package com.jammali.cryptocurrency.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface CoinsListDao {

    //Returns all stocks
    @Query("Select * from coins_list")
    fun getCoinsList(): Flow<List<CoinsListEntity>>

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinList: List<CoinsListEntity>)

    //Update the row
    @Update
    suspend fun updateCoinsListEntity(data: CoinsListEntity): Int

    //Delete all rows
    @Query("Delete from coins_list")
    suspend fun deleteAll()

}
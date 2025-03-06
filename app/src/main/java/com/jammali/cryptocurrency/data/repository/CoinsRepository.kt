package com.jammali.cryptocurrency.data.repository

import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [coins_list] from a given data source.
 */
interface CoinsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllCoinsStream(): Flow<List<CoinsListEntity>>


    /**
     * Insert item in the data source
     */
    suspend fun insertCoins(coin: List<CoinsListEntity>)

    /**
     * Delete All coins
     */
    suspend fun deleteAll()

}

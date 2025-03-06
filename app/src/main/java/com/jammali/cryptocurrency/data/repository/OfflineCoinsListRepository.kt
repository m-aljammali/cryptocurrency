package com.jammali.cryptocurrency.data.repository

import com.jammali.cryptocurrency.data.local.database.CoinsListDao
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import kotlinx.coroutines.flow.Flow

class OfflineCoinsListRepository (private val coinsListDao: CoinsListDao) : CoinsRepository {
    override fun getAllCoinsStream(): Flow<List<CoinsListEntity>> = coinsListDao.getCoinsList()

    override suspend fun insertCoins(coins: List<CoinsListEntity>) = coinsListDao.insert(coins)

    override suspend fun deleteAll() = coinsListDao.deleteAll()

    }
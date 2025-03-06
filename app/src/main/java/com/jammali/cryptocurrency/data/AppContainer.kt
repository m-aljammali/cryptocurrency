package com.jammali.cryptocurrency.data

import android.content.Context
import android.util.Log
import com.jammali.cryptocurrency.data.local.database.CoinsDatabase
import com.jammali.cryptocurrency.data.repository.CoinsListRemoteDataSource
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import com.jammali.cryptocurrency.data.repository.CoinsRepository
import com.jammali.cryptocurrency.data.repository.OfflineCoinsListRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val coinsRepository: CoinsListRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineCoinsListRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [CoinsRepository]
     */

    val TAG = "AppDataContainer"
    override val coinsRepository: CoinsListRepository by lazy {
        Log.d(TAG,"AppDataContainer")
        CoinsListRepository (CoinsListRemoteDataSource(), OfflineCoinsListRepository(CoinsDatabase.getDatabase(context).coinsListDao()))
      //  OfflineCoinsListRepository(CoinsDatabase.getDatabase(context).coinsListDao())
    }


}

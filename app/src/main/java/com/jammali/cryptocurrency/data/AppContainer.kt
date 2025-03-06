package com.jammali.cryptocurrency.data

import android.content.Context
import android.util.Log
import com.jammali.cryptocurrency.data.api.ApiInterface
import com.jammali.cryptocurrency.data.local.database.CoinsDatabase
import com.jammali.cryptocurrency.data.repository.CoinsListRemoteDataSource
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import com.jammali.cryptocurrency.data.repository.CoinsRepository
import com.jammali.cryptocurrency.data.repository.OfflineCoinsListRepository
import com.jammali.cryptocurrency.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

         val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiInterface::class.java)


         val remoteDataSource = CoinsListRemoteDataSource(retrofit)
         val localDataSource = OfflineCoinsListRepository(CoinsDatabase.getDatabase(context).coinsListDao())

        CoinsListRepository (remoteDataSource, localDataSource)
      // OfflineCoinsListRepository(CoinsDatabase.getDatabase(context).coinsListDao())
    }


}

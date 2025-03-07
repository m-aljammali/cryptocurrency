package com.jammali.cryptocurrency.data.repository

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.jammali.cryptocurrency.data.api.Result
import com.jammali.cryptocurrency.data.api.succeeded
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.data.storePreference
import com.jammali.cryptocurrency.utils.CacheUtils
import com.jammali.cryptocurrency.utils.Constants
import kotlinx.coroutines.flow.Flow


class CoinsListRepository (
    private val coinsListRemoteDataSource: CoinsListRemoteDataSource,
    private val coinsListDataSource: OfflineCoinsListRepository,
) {


    suspend fun coinsList(targetCur: String , marketCapDesc: String, perPage: String, page: String, context: Context) {
        val toast =
            Toast.makeText(context, Constants.GENERIC_ERROR, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        when (val result = coinsListRemoteDataSource.coinsList(targetCur, marketCapDesc , perPage, page )) {
            is Result.Success -> {
                if (result.succeeded) {

                    val customCoinsList = result.data.let {
                        Log.d("CoinsListRepository", "coinsList")
                        it.filter { item -> item.symbol.isNullOrEmpty().not() }
                            .map { item ->
                                Log.d("CoinsListRepository", "item: ${item}")
                                CoinsListEntity(

                                    item.symbol ?: "",
                                    item.id,
                                    item.name,
                                    item.price,
                                    item.changePercent,
                                    item.image,
                                )
                            }
                    }

                    coinsListDataSource.insertCoins(customCoinsList)
                    storePreference.saveStringValue(CacheUtils.getCurrentTime(), storePreference.CACHE_EXP, context)

                    Result.Success(true)
                } else {

                    toast.show()
                    Log.d("CoinsListRepository", "error")
                    Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> {

                toast.show()

                result as Result.Error}
        }
    }



     fun getAllCoinsStream() : Flow<List<CoinsListEntity>> {
     return coinsListDataSource.getAllCoinsStream()
    }

    suspend fun deleteCache()  {
         coinsListDataSource.deleteAll()
    }


}
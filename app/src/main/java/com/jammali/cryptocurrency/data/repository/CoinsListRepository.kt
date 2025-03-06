package com.jammali.cryptocurrency.data.repository

import android.util.Log
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.utils.Constants
import com.jammali.cryptocurrency.data.api.Result
import com.jammali.cryptocurrency.data.api.succeeded
import kotlinx.coroutines.flow.Flow


class CoinsListRepository (
    private val coinsListRemoteDataSource: CoinsListRemoteDataSource,
    private val coinsListDataSource: OfflineCoinsListRepository,
) {


    suspend fun coinsList(targetCur: String) {

        Log.d("CoinsListRepository", "coinsList")
        when (val result = coinsListRemoteDataSource.coinsList(targetCur)) {
            is Result.Success -> {
                if (result.succeeded) {

                    val customStockList = result.data.let {
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

                    coinsListDataSource.insertCoins(customStockList)


                    Result.Success(true)
                } else {
                    Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }


     fun getAllCoinsStream() : Flow<List<CoinsListEntity>> {
     return coinsListDataSource.getAllCoinsStream()
    }


}
package com.jammali.cryptocurrency.data.repository

import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.utils.Constants
import com.jammali.cryptocurrency.data.api.Result
import com.jammali.cryptocurrency.data.api.succeeded


class CoinsListRepository (
    private val coinsListRemoteDataSource: CoinsListRemoteDataSource,
    private val coinsListDataSource: OfflineCoinsListRepository,
) {


    suspend fun coinsList(targetCur: String) {
        when (val result = coinsListRemoteDataSource.coinsList(targetCur)) {
            is Result.Success -> {
                if (result.succeeded) {

                    val customStockList = result.data.let {
                        it.filter { item -> item.symbol.isNullOrEmpty().not() }
                            .map { item ->
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


}
package com.jammali.cryptocurrency.data.repository


import com.jammali.cryptocurrency.data.api.ApiInterface
import com.jammali.cryptocurrency.data.api.BaseRemoteDataSource
import com.jammali.cryptocurrency.data.api.Coin
import com.jammali.cryptocurrency.data.api.Result



class CoinsListRemoteDataSource (private val service: ApiInterface) :
    BaseRemoteDataSource() {

    suspend fun coinsList(targetCur: String , marketCapDesc: String, perPage: String, page : String ): Result<List<Coin>> =
        getResult {
            service.coinsList(targetCur, marketCapDesc , perPage, page)
        }
}
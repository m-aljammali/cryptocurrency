package com.jammali.cryptocurrency.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET(EndPoints.COINS_LIST)
    suspend fun coinsList(@Query("vs_currency") targetCurrency: String): Response<List<Coin>>


}
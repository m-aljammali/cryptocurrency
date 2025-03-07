package com.jammali.cryptocurrency.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET(EndPoints.COINS_LIST)
    suspend fun coinsList(@Query("vs_currency") targetCurrency: String, @Query("order") market_cap_desc: String, @Query("per_page") per_page: String , @Query("page") page: String): Response<List<Coin>>


}
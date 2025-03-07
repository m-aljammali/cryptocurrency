package com.jammali.cryptocurrency


import android.app.Application
import android.content.Context
import com.jammali.cryptocurrency.data.AppContainer
import com.jammali.cryptocurrency.data.AppDataContainer

class CryptocurrencyApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer


    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)


    }
}
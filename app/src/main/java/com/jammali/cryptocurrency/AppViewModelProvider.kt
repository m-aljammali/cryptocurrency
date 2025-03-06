package com.jammali.cryptocurrency

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {

    val Factory = viewModelFactory {


        // Initializer for CoinsListViewModel
        initializer {
            CoinsListViewModel(inventoryApplication().container.coinsRepository)
        }

    }

}


/**
 * Extension function to queries for [Application] object and returns an instance of
 * [CryptocurrencyApplication].
 */
fun CreationExtras.inventoryApplication(): CryptocurrencyApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CryptocurrencyApplication)

package com.jammali.cryptocurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsListViewModel (private val  coinsListRepository : CoinsListRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            coinsListRepository.coinsList("usd")
        }
    }


}
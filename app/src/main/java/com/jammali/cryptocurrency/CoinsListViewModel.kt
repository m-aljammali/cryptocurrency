package com.jammali.cryptocurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoinsListViewModel (private val  coinsListRepository : CoinsListRepository) : ViewModel() {




    val homeUiState: StateFlow<HomeUiState> =
        coinsListRepository.getAllCoinsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    init {
        viewModelScope.launch {
            coinsListRepository.coinsList("usd")



        }
    }


}



/**
 * Ui State for ListScreen
 */
data class HomeUiState(val coinsList: List<CoinsListEntity> = listOf())

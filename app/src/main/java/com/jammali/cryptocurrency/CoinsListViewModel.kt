package com.jammali.cryptocurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoinsListViewModel (private val  coinsListRepository : CoinsListRepository) : ViewModel() {




    val listUiState: StateFlow<ListUiState> =
        coinsListRepository.getAllCoinsStream().map { ListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ListUiState()
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
data class ListUiState(val coinsList: List<CoinsListEntity> = listOf())

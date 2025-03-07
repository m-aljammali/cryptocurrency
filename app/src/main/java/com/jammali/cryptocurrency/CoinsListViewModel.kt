package com.jammali.cryptocurrency

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.data.repository.CoinsListRepository
import com.jammali.cryptocurrency.data.storePreference
import com.jammali.cryptocurrency.utils.CacheUtils
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoinsListViewModel (private val  coinsListRepository : CoinsListRepository, context: Context) : ViewModel() {



// link table with UI list
    val listUiState: StateFlow<ListUiState> =
        coinsListRepository.getAllCoinsStream().map { ListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ListUiState()
            )

init {
    viewModelScope.launch {

        storePreference.getCacheExp(context)
            .collect {
                if (it != null) {
                    if (CacheUtils.hasCacheExp(it)) {
                    deleteCache()

                    }
                }
            }

    }

    viewModelScope.launch {

        coinsListRepository.coinsList("usd", "market_cap_Desc" , "5" , "1", context)

    }
}

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


    fun getCoinsFromRemote(perPage : String, context: Context) {

        viewModelScope.launch {
            coinsListRepository.coinsList("usd", "market_cap_Desc" , perPage , "1", context)

        }


    }
    fun deleteCache() {

        viewModelScope.launch {
            coinsListRepository.deleteCache()

        }
    }

}



/**
 * Ui State for ListScreen
 */
data class ListUiState(val coinsList: List<CoinsListEntity> = listOf())

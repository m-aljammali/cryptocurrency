package com.jammali.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jammali.cryptocurrency.data.local.database.CoinsListEntity
import com.jammali.cryptocurrency.ui.theme.CryptocurrencyTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrencyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                    TopAppBar(
                        title = "Crypto Currency",
                    ) },

                    ) { innerPadding ->

                    HomeScreen( modifier = Modifier.padding(innerPadding),
                        contentPadding = innerPadding,)
                }

            }
        }
    }
}



@Composable
fun HomeScreen(

    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: CoinsListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.listUiState.collectAsState()


    Scaffold(


    ) { innerPadding ->
        HomeBody(
            coinsList = homeUiState.coinsList,
           // onItemClick = navigateToItemUpdate,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}

@Composable
private fun HomeBody(
    coinsList: List<CoinsListEntity>,
   // onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (coinsList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            InventoryList(
                coinsList = coinsList,
              //  onItemClick = { onItemClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun InventoryList(
    coinsList: List<CoinsListEntity>,
  //  onItemClick: (CoinsListEntity) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = coinsList, key = { it.id }) { item ->
            CoinItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small)))
                 //   .clickable { onItemClick(item) })
        }
    }
}


@Composable
private fun CoinItem(
    item: CoinsListEntity, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        val textColor = if (item.changePercent!! > 0 ) Color(0xFF4CAF50) else Color(0xFFFF2934)
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.image,
                contentDescription = "Translated description of what the image contains"
            )
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.name!!,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text =  "$${item.price!!} ",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text =  item.changePercent.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor
                )
            }
        }


    }
}

@ExperimentalMaterial3Api
@Composable
fun TopAppBar(
    title: String,

) {
    CenterAlignedTopAppBar(
        title =  { Text(title, style = MaterialTheme.typography.headlineMedium )  } ,

    )
}


@Preview(showBackground = true)
@Composable
fun CoinItem() {
    CryptocurrencyTheme {
        CoinItem(
            CoinsListEntity(1, "xrp", "ripple", "XRP", 2.58, 2.94662, "https://coin-images.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1696501442" ),
        )
    }
}
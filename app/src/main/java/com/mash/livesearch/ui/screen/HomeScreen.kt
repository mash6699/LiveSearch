package com.mash.livesearch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mash.livesearch.R
import com.mash.livesearch.ui.component.EmptyView
import com.mash.livesearch.ui.component.ErrorView
import com.mash.livesearch.ui.component.FilterList
import com.mash.livesearch.ui.component.LoadingIndicator
import com.mash.livesearch.ui.component.ProductList
import com.mash.livesearch.ui.component.SearchBarApp
import com.mash.livesearch.ui.component.WelcomeView
import com.mash.livesearch.ui.theme.LiveSearchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val searchText by viewModel.searchText.collectAsState()
    val sortOptions by viewModel.filterList.collectAsState()
    val products by viewModel.productList.collectAsState()
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    fontStyle = FontStyle.Normal
                ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }) {

        Column(modifier = Modifier.padding(it)) {
            SearchBarApp(
                modifier = Modifier.padding(10.dp),
                text = searchText,
                onTextChange = viewModel::onSearchTextChange,
                placeHolder = stringResource(id = R.string.search_placeholder),
                onCloseClicked = viewModel::onClearText,
                onSearchClicked = {
                    viewModel::onSearchTextChange
                }
            )

            FilterList(
                sortOptions = sortOptions,
                onSelectionChange = viewModel::onFilterSelected
            )
            Spacer(modifier = Modifier.height(5.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when (uiState) {
                    is HomeState.Idle -> WelcomeView()

                    is HomeState.Fetch -> LoadingIndicator()

                    is HomeState.Success -> {
                        if (products.isEmpty()) {
                            EmptyView()
                        } else {
                            ProductList(products)
                        }
                    }

                    is HomeState.Error -> ErrorView(text = uiState.apiException.message.orEmpty())
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAppPreview() {
    LiveSearchTheme {
        HomeApp()
    }
}
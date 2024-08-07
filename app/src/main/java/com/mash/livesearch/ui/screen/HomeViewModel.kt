package com.mash.livesearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mash.livesearch.domain.BaseResult
import com.mash.livesearch.domain.model.Filter
import com.mash.livesearch.domain.model.ProductRecord
import com.mash.livesearch.domain.toFilter
import com.mash.livesearch.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {

    companion object {
        const val EMPTY_TEXT = ""
        const val FILTER_TEXT = "precio"
        const val DEFAULT_FILTER = "0"
        const val SEPARATOR = "|"
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _filterList = MutableStateFlow<List<Filter>>(emptyList())
    val filterList = _filterList.asStateFlow()

    private val _productList = MutableStateFlow<List<ProductRecord>>(emptyList())
    val productList = _productList.asStateFlow()

    var uiState: HomeState by mutableStateOf(HomeState.Idle)
        private set

    private var searchTerm by mutableStateOf(EMPTY_TEXT)
    private var sortOption by mutableStateOf(EMPTY_TEXT)

    private fun fetchSearch(term: String) = viewModelScope.launch {
        searchTerm = term
        uiState = HomeState.Fetch
        when (val result = useCase.getSearchByTerm(term, sortOption)) {
            is BaseResult.Success -> {
                _filterList.value = result.data.plpResults.sortOptions.filter {
                    it.label.contains(FILTER_TEXT)
                }.map { it.toFilter() }
                _productList.value = result.data.plpResults.records!!
                uiState = HomeState.Success
            }

            is BaseResult.Error -> {
                uiState = HomeState.Error(result.exception)

            }
        }
        _isSearching.value = false
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _isSearching.value = true
        _productList.value = emptyList()

        if (text.isNullOrBlank()) {
            _filterList.value = emptyList()
            uiState = HomeState.Idle
            return
        }
        fetchSearch(text)
    }

    fun onFilterSelected(filterName: String?) {
        sortOption = if (filterName.isNullOrBlank()) {
            EMPTY_TEXT
        } else {
            _filterList.value.firstOrNull { it.label == filterName }
                ?.sort?.split(SEPARATOR)?.get(1) ?: DEFAULT_FILTER
        }
        onSearchTextChange(searchTerm)
    }

    fun onClearText() {
        _filterList.value = emptyList()
        _productList.value = emptyList()
        _searchText.value = EMPTY_TEXT
        uiState = HomeState.Idle
    }
}
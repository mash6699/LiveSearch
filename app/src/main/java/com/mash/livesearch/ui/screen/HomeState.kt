package com.mash.livesearch.ui.screen

import com.mash.livesearch.domain.ApiException

sealed class HomeState {
    object Idle : HomeState()
    object Fetch: HomeState()
    object Success: HomeState()
    class Error(val apiException: ApiException): HomeState()
}
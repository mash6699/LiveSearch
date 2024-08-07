package com.mash.livesearch.domain

sealed interface BaseResult<out T> {
    data class Success<out  T>(val data: T): BaseResult<T>
    data class Error(val exception: ApiException): BaseResult<Nothing>
}
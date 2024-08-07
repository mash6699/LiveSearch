package com.mash.livesearch.domain.repository

import com.mash.livesearch.domain.BaseResult
import com.mash.livesearch.domain.model.APIProductResponse

interface ProductRepository {
    suspend fun getSearch(search: String): BaseResult<APIProductResponse>
    suspend fun getOrderedSearch(search: String, sort: String): BaseResult<APIProductResponse>
}
package com.mash.livesearch.domain.usecase

import com.mash.livesearch.domain.BaseResult
import com.mash.livesearch.domain.model.APIProductResponse
import com.mash.livesearch.domain.repository.ProductRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend fun getSearchByTerm(term: String, sort: String?): BaseResult<APIProductResponse> {

        return if (sort.isNullOrBlank()) {
            repository.getSearch(term)
        } else {
            repository.getOrderedSearch(term, sort)
        }

    }
}
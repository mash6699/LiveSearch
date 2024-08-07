package com.mash.livesearch.domain.repository

import com.mash.livesearch.R
import com.mash.livesearch.domain.ApiException
import com.mash.livesearch.domain.BaseResult
import com.mash.livesearch.domain.model.APIProductResponse
import com.mash.livesearch.network.APIService
import com.mash.livesearch.util.NetworkStatus
import com.mash.livesearch.util.StringResource
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val apiService: APIService,
    private val networkStatus: NetworkStatus,
    private val resource: StringResource
): ProductRepository {

    override suspend fun getSearch(search: String): BaseResult<APIProductResponse> {
       val connectionError = resource.getString(R.string.network_error)
        return if (networkStatus.isNetworkAvailable()) {
            try {
                val response = apiService.getSearchProduct(search)
                BaseResult.Success(response)

            } catch (e: ApiException) {
                BaseResult.Error(e)
            }
        } else {
            BaseResult.Error(ApiException(-1, connectionError))
        }
    }

    override suspend fun getOrderedSearch(search: String, order: String): BaseResult<APIProductResponse> {
        val connectionError = resource.getString(R.string.network_error)
        return if (networkStatus.isNetworkAvailable()) {
            try {
                val response = apiService.getSearchForOrderedProduct(search, shortPrice = order)
                BaseResult.Success(response)
            } catch (e: ApiException) {
                BaseResult.Error(e)
            }
        } else {
            BaseResult.Error(ApiException(-1, connectionError))
        }
    }
}
package com.mash.livesearch.network

import com.mash.livesearch.domain.model.APIProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("appclienteservices/services/v3/plp")
    suspend fun getSearchProduct(
        @Query("search-string") search: String,
        @Query("page-number") page: Int = 1
    ): APIProductResponse

    @GET("appclienteservices/services/v3/plp")
    suspend fun getSearchForOrderedProduct(
        @Query("search-string") search: String,
        @Query("page-number") page: Int = 1,
        @Query("minSortPrice") shortPrice: String
    ): APIProductResponse

}
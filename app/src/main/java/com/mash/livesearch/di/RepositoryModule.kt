package com.mash.livesearch.di

import com.mash.livesearch.domain.repository.ProductRepository
import com.mash.livesearch.domain.repository.ProductRepositoryImp
import com.mash.livesearch.network.APIService
import com.mash.livesearch.util.NetworkStatus
import com.mash.livesearch.util.StringResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        apiService: APIService,
        networkStatus: NetworkStatus,
        resource: StringResource
    ): ProductRepository = ProductRepositoryImp(apiService, networkStatus, resource)
}
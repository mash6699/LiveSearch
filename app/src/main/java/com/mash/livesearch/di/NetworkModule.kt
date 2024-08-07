package com.mash.livesearch.di

import com.mash.livesearch.BuildConfig
import com.mash.livesearch.domain.ErrorInterceptor
import com.mash.livesearch.network.APIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.API_URL

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            addInterceptor(ErrorInterceptor())
        }.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}
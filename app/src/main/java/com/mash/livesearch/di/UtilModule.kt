package com.mash.livesearch.di

import android.content.Context
import com.mash.livesearch.util.NetworkStatus
import com.mash.livesearch.util.NetworkUtil
import com.mash.livesearch.util.StringResource
import com.mash.livesearch.util.StringResourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideNetworkUtil(@ApplicationContext context: Context): NetworkStatus =
         NetworkUtil(context)

    @Provides
    @Singleton
    fun provideStringResources(@ApplicationContext context: Context): StringResource =
         StringResourceImp(context.resources)

}
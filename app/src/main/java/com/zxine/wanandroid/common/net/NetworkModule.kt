package com.zxine.wanandroid.common.net

import com.zxine.wanandroid.net.api.WanAndroidNetService
import com.zxine.wanandroid.net.client.WanAndroidApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): WanAndroidNetService = WanAndroidApiClient.netService

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = WanAndroidApiClient.okHttp
}
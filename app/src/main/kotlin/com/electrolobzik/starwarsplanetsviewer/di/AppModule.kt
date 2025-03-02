package com.electrolobzik.starwarsplanetsviewer.di

import com.electrolobzik.starwarsplanetsviewer.core.coroutines.DefaultDispatcherProvider
import com.electrolobzik.starwarsplanetsviewer.core.coroutines.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}
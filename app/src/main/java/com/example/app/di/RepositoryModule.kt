package com.example.app.di

import com.example.app.data.ProductRepositoryImpl
import com.example.app.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository
}
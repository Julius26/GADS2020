package com.vesencom.gadsphaseii.di

import com.vesencom.gadsphaseii.network.networking.RemoteDataSource
import com.vesencom.gadsphaseii.network.networking.RemoteDataSourceImpl
import com.vesencom.gadsphaseii.repositories.MainRepository
import com.vesencom.gadsphaseii.repositories.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
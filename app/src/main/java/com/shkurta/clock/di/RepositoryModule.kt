package com.shkurta.clock.di

import com.shkurta.clock.data.repository.AlarmRepository
import com.shkurta.clock.data.repository.AlarmRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAlarmRepository(impl: AlarmRepositoryImpl): AlarmRepository
}
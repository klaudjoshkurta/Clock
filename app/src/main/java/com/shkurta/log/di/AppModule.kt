package com.shkurta.log.di

import android.content.Context
import androidx.room.Room
import com.shkurta.log.data.local.db.core.LogDatabase
import com.shkurta.log.data.local.db.dao.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLogDatabase(@ApplicationContext context: Context): LogDatabase {
        return Room.databaseBuilder(
            context,
            LogDatabase::class.java,
            "log_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLogDao(logDatabase: LogDatabase): LogDao = logDatabase.logDao()
}
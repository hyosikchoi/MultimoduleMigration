package com.hyosik.android.diary.di

import android.content.Context
import androidx.room.Room
import com.hyosik.android.diary.data.local.db.AppDatabase
import com.hyosik.android.diary.data.local.db.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(applicationContext , AppDatabase::class.java , AppDatabase.DB_NAME)
            .addMigrations(AppDatabase.MIGRATION_1_TO_2)
            .build()
    }

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase) : TodoDao {
        return appDatabase.dao()
    }

}
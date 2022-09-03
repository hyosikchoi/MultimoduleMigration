package com.hyosik.android.diary.di

import com.hyosik.android.data.local.TodoListRepositoryImpl
import com.hyosik.android.data.local.db.TodoDao
import com.hyosik.android.domain.repository.TodoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesTodoListRepository(todoDao: TodoDao) : TodoListRepository {
        return TodoListRepositoryImpl(todoDao = todoDao)
    }

}
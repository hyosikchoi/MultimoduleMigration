package com.hyosik.android.diary.di

import com.hyosik.android.diary.data.local.TodoListRepositoryImpl
import com.hyosik.android.diary.data.local.db.TodoDao
import com.hyosik.android.diary.domain.repository.TodoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesTodoListRepository(todoDao: TodoDao , coroutineDispatcher: CoroutineDispatcher) : TodoListRepository {
        return TodoListRepositoryImpl(todoDao = todoDao , ioDispatcher = coroutineDispatcher)
    }

}
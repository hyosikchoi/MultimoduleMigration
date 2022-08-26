package com.hyosik.android.diary.di

import com.hyosik.android.diary.domain.repository.TodoListRepository
import com.hyosik.android.diary.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetTodoListUseCase(todoListRepository: TodoListRepository) : GetTodoListUsecase {
        return GetTodoListUsecase(todoListRepository = todoListRepository)
    }

    @Provides
    fun providesInsertTodoUseCase(todoListRepository: TodoListRepository) : InsertTodoUseCase  {
        return InsertTodoUseCase(todoListRepository = todoListRepository)
    }

    @Provides
    fun providesGetTodoUseCase(todoListRepository: TodoListRepository) : GetTodoUseCase {
        return GetTodoUseCase(todoListRepository = todoListRepository)
    }

    @Provides
    fun providesUpdateTodoUseCase(todoListRepository: TodoListRepository) : UpdateTodoUseCase {
        return UpdateTodoUseCase(todoListRepository = todoListRepository)
    }

    @Provides
    fun providesDeleteTodoUseCase(todoListRepository: TodoListRepository) : DeleteTodoUseCase {
        return DeleteTodoUseCase(todoListRepository = todoListRepository)
    }

}
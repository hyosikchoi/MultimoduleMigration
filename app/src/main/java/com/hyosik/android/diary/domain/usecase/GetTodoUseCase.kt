package com.hyosik.android.diary.domain.usecase

import com.hyosik.android.diary.domain.model.Todo
import com.hyosik.android.diary.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow

class GetTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        id : Long
    ) : Flow<Todo> = todoListRepository.getTodo(id = id)

}
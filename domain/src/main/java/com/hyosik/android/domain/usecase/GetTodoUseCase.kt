package com.hyosik.android.domain.usecase

import com.hyosik.android.domain.model.Todo
import com.hyosik.android.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow

class GetTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        id : Long
    ) : Flow<Todo> = todoListRepository.getTodo(id = id)

}
package com.hyosik.android.domain.usecase

import com.hyosik.android.domain.model.Todo
import com.hyosik.android.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow

class InsertTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(todo: Todo) {
        todoListRepository.insertTodo(todo)
    }

}
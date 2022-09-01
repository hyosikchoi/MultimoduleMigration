package com.hyosik.android.domain.usecase

import com.hyosik.android.domain.model.Todo
import com.hyosik.android.domain.repository.TodoListRepository


class UpdateTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        todo : Todo
    ) {
        todoListRepository.updateTodo(todo = todo)
    }

}
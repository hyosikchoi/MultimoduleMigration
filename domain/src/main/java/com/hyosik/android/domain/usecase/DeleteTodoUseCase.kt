package com.hyosik.android.domain.usecase

import com.hyosik.android.domain.repository.TodoListRepository


class DeleteTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        id : Long
    ) {
        todoListRepository.deleteTodo(id = id)
    }

}
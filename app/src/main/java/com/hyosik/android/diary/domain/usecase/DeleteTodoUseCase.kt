package com.hyosik.android.diary.domain.usecase

import com.hyosik.android.diary.domain.repository.TodoListRepository

class DeleteTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        id : Long
    ) {
        todoListRepository.deleteTodo(id = id)
    }

}
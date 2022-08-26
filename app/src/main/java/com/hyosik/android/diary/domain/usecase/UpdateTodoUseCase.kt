package com.hyosik.android.diary.domain.usecase

import com.hyosik.android.diary.domain.model.Todo
import com.hyosik.android.diary.domain.repository.TodoListRepository

class UpdateTodoUseCase(
    private val todoListRepository: TodoListRepository
) {

    operator fun invoke(
        todo : Todo
    ) {
        todoListRepository.updateTodo(todo = todo)
    }

}
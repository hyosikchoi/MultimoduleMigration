package com.hyosik.android.diary.domain.usecase

import com.hyosik.android.diary.domain.model.Todo
import com.hyosik.android.diary.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow

class GetTodoListUsecase(
    private val todoListRepository: TodoListRepository
) {
    operator fun invoke() : Flow<List<Todo>>  = todoListRepository.getTodoList()
}
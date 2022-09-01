package com.hyosik.android.domain.usecase

data class TodoUseCases(
    val getTodoList : GetTodoListUsecase,
    val getTodo : GetTodoUseCase,
    val insertTodo : InsertTodoUseCase,
    val updateTodo : UpdateTodoUseCase,
    val deleteTodo : DeleteTodoUseCase
)

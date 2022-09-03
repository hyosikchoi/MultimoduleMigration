package com.hyosik.android.diary.presentation.mapper

import com.hyosik.android.diary.presentation.model.TodoVO
import com.hyosik.android.domain.model.Todo

fun List<Todo>.toTodoVO() : List<TodoVO> =
    this.map { todoEntity ->
        TodoVO(
            _id = todoEntity.id,
            _description = todoEntity.description,
            _lock = todoEntity.lock,
            _hasCompleted = todoEntity.hasCompleted,
            _title = todoEntity.title,
            _password = todoEntity.password
        )
    }

fun Todo.toTodoVO() : TodoVO =
    TodoVO(
        _id = this.id,
        _title = this.title,
        _description = this.description,
        _hasCompleted = this.hasCompleted,
        _lock = this.lock,
        _password = this.password
    )
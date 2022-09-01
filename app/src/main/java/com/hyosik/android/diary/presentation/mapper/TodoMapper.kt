package com.hyosik.android.diary.presentation.mapper

import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.domain.model.Todo

fun List<Todo>.toTodoModel() : List<TodoModel> =
    this.map { todoEntity ->
        TodoModel(
            _id = todoEntity.id,
            _description = todoEntity.description,
            _lock = todoEntity.lock,
            _hasCompleted = todoEntity.hasCompleted,
            _title = todoEntity.title,
            _password = todoEntity.password
        )
    }

fun Todo.toTodoModel() : TodoModel =
    TodoModel(
        _id = this.id,
        _title = this.title,
        _description = this.description,
        _hasCompleted = this.hasCompleted,
        _lock = this.lock,
        _password = this.password
    )
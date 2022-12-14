package com.hyosik.android.data.local.mapper

import com.hyosik.android.data.local.db.TodoEntity
import com.hyosik.android.data.local.model.TodoModel
import com.hyosik.android.domain.model.Todo

fun List<TodoEntity>.toTodo() : List<TodoModel> =
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

fun Todo.toTodoEntity() : TodoEntity =
    TodoEntity(
        title = this.title,
        description = this.description,
        hasCompleted = this.hasCompleted,
        lock = this.lock,
        password = this.password
    )

fun TodoEntity.toTodo() : TodoModel =
    TodoModel(
        _id = this.id,
        _title = this.title,
        _description = this.description,
        _hasCompleted = this.hasCompleted,
        _lock = this.lock,
        _password = this.password
    )
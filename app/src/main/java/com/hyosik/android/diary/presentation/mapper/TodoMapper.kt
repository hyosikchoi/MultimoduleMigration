package com.hyosik.android.diary.presentation.mapper

import com.hyosik.android.diary.data.local.db.TodoEntity
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.domain.model.Todo

fun List<Todo>.toTodoModel() : List<TodoModel> =
    this.map { todoEntity ->
        TodoModel(
            _id = todoEntity.id,
            _description = todoEntity.description,
            _lock = todoEntity.lock,
            _hasCompleted = todoEntity.hasCompleted,
            _title = todoEntity.title
        )
    }
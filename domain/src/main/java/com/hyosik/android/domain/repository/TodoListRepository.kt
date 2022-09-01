package com.hyosik.android.domain.repository

import com.hyosik.android.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {

    fun getTodoList() : Flow<List<Todo>>

    fun getTodo(id : Long) : Flow<Todo>

    fun insertTodo(todo: Todo)

    fun updateTodo(todo: Todo)

    fun deleteTodo(id : Long)

}
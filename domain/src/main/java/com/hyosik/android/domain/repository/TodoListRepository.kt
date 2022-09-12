package com.hyosik.android.domain.repository

import com.hyosik.android.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {

    fun getTodoList() : Flow<List<Todo>>

    fun getTodo(id : Long) : Flow<Todo>

    suspend fun insertTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(id : Long)

}
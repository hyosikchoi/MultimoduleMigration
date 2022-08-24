package com.hyosik.android.diary.domain.repository

import com.hyosik.android.diary.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {

    suspend fun getTodoList() : Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)

    fun updateTodo(todo: Todo)

    fun deleteTodo(id : Long)

}
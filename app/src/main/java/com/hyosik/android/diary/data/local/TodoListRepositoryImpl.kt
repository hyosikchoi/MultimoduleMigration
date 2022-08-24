package com.hyosik.android.diary.data.local

import com.hyosik.android.diary.data.local.db.TodoDao
import com.hyosik.android.diary.data.local.mapper.toTodo
import com.hyosik.android.diary.data.local.mapper.toTodoEntity
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.domain.model.Todo
import com.hyosik.android.diary.domain.repository.TodoListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val ioDispatcher: CoroutineDispatcher
) : TodoListRepository {

    override suspend fun getTodoList(): Flow<List<Todo>> {
        return todoDao.getAllList().map { todoEntityList ->
            todoEntityList.toTodo()
        }
    }

    override suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todoEntity = todo.toTodoEntity())
    }

    override fun updateTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(id: Long) {
        TODO("Not yet implemented")
    }
}
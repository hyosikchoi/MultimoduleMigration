package com.hyosik.android.diary.data.local

import com.hyosik.android.diary.data.local.db.TodoDao
import com.hyosik.android.diary.data.local.mapper.toTodo
import com.hyosik.android.diary.data.local.mapper.toTodoEntity
import com.hyosik.android.domain.model.Todo
import com.hyosik.android.domain.repository.TodoListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val ioDispatcher: CoroutineDispatcher
) : TodoListRepository {

    override fun getTodoList(): Flow<List<Todo>> {
        return todoDao.getAllList().map { todoEntityList ->
            todoEntityList.toTodo()
        }
    }

    override fun getTodo(id :Long): Flow<Todo> {
        return todoDao.getById(id = id).map { todo ->
            todo.toTodo()
        }
    }

    override fun insertTodo(todo: Todo) {
        todoDao.insert(todoEntity = todo.toTodoEntity())
    }

    override fun updateTodo(todo: Todo) {
        todoDao.update(
            id = todo.id!!,
            title = todo.title,
            description = todo.description,
            hasComplete = todo.hasCompleted,
            lock = todo.lock
        )
    }

    override fun deleteTodo(id: Long) {
        todoDao.delete(id = id)
    }
}
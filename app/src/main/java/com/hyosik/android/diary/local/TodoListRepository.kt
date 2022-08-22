package com.hyosik.android.diary.local

import com.hyosik.android.diary.local.db.TodoDao
import javax.inject.Inject

class TodoListRepository @Inject constructor(
   private val todoDao: TodoDao
) {

}
package com.hyosik.android.diary.local.db

import androidx.room.*
import com.hyosik.android.diary.local.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity")
    suspend fun getAllList() : List<TodoEntity>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getById(id : Long) : TodoEntity?

    @Insert
    suspend fun insert(todoEntity: TodoEntity) : Long

    @Update
    suspend fun update(todoEntity: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun delete(id : Long)

}
package com.hyosik.android.diary.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity")
    fun getAllList() : Flow<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getById(id : Long) : TodoEntity?

    @Insert
    fun insert(todoEntity: TodoEntity)

    @Update
    suspend fun update(todoEntity: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun delete(id : Long)

}
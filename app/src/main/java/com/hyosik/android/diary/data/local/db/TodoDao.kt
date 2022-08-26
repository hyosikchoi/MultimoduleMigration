package com.hyosik.android.diary.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity")
    fun getAllList() : Flow<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    fun getById(id : Long) : Flow<TodoEntity>

    @Insert
    fun insert(todoEntity: TodoEntity)

    @Query("UPDATE TodoEntity SET title = :title , description = :description , hasCompleted = :hasComplete , lock = :lock WHERE id = :id")
    fun update(id : Long , title : String , description : String , hasComplete : Boolean , lock : Boolean)

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun delete(id : Long)

}
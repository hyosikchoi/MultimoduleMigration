package com.hyosik.android.diary.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hyosik.android.diary.local.model.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDatabase_DB"
    }

    abstract fun dao() : TodoDao

}
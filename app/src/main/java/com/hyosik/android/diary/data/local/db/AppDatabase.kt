package com.hyosik.android.diary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

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
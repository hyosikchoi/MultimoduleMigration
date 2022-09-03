package com.hyosik.android.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [TodoEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDatabase_DB"

        val MIGRATION_1_TO_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    ALTER TABLE TodoEntity ADD COLUMN password VARCHAR(200) NOT NULL default ""
                """.trimIndent())
            }
        }
    }

    abstract fun dao() : TodoDao

}
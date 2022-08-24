package com.hyosik.android.diary.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0 ,

    val title : String,
    val description : String,
    val hasCompleted : Boolean = false,
    val lock : Boolean = false
)

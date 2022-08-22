package com.hyosik.android.diary.local.model

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

package com.hyosik.android.domain.model

interface Todo {
    val id : Long?
    val title : String
    val description : String
    val hasCompleted : Boolean
    val lock : Boolean
    val password : String
}
package com.hyosik.android.diary.data.local.model

import com.hyosik.android.domain.model.Todo


data class TodoModel(
    private val _id : Long?,
    private val _title : String,
    private val _description : String,
    private val _hasCompleted : Boolean,
    private val _lock : Boolean,
    private val _password : String
) : Todo {
    override val id: Long?
        get() = _id
    override val title: String
        get() = _title
    override val description: String
        get() = _description
    override val hasCompleted: Boolean
        get() = _hasCompleted
    override val lock: Boolean
        get() = _lock
    override val password: String
        get() = _password
}

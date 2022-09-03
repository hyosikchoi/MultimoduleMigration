package com.hyosik.android.presentation.state

import com.hyosik.android.presentation.model.TodoVO

sealed class UiState {
    open val cause : String = ""
    open val todoList : List<TodoVO> = emptyList()

    val isLoading: Boolean get() = this is Loading
    val isEmpty: Boolean get() = this is Empty
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    object UnInitialized : UiState()
    object Loading : UiState()
    object Empty : UiState()

    data class Success(
        override val todoList: List<TodoVO>
    ) : UiState()

    data class Error(
        override val cause : String
    ) : UiState()
}

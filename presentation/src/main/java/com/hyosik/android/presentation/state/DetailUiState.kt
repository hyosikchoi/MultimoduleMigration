package com.hyosik.android.presentation.state

import com.hyosik.android.presentation.model.TodoVO

sealed class DetailUiState {

    open val cause : String = ""
    open val todo : TodoVO? = null

    val isLoading: Boolean get() = this is Loading
    val isEmpty: Boolean get() = this is Empty
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    object UnInitialized : DetailUiState()
    object Loading : DetailUiState()
    object Empty : DetailUiState()

    data class Success(
        override val todo: TodoVO
    ) : DetailUiState()

    data class Error(
        override val cause : String
    ) : DetailUiState()

}

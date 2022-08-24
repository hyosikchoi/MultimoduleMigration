package com.hyosik.android.diary.presentation.state

import com.hyosik.android.diary.domain.model.Todo

sealed class UiState {
    open val cause : String = ""
    open val dailyWeatherList : List<Todo> = emptyList()

    val isLoading: Boolean get() = this is Loading
    val isEmpty: Boolean get() = this is Empty
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    object UnInitialized : UiState()
    object Loading : UiState()
    object Empty : UiState()

    data class Success(
        override val dailyWeatherList: List<Todo>
    ) : UiState()

    data class Error(
        override val cause : String
    ) : UiState()
}

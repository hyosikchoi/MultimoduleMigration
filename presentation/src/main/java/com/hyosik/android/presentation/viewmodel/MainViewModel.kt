package com.hyosik.android.presentation.viewmodel

import android.util.Log
import com.hyosik.android.presentation.state.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.domain.usecase.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toTodoVO
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.UnInitialized)

    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    private val _splashState = MutableStateFlow<Boolean>(false)

    val splashState = _splashState.asStateFlow()

    fun fetchTodoList() = viewModelScope.launch {
        todoUseCases.getTodoList()
            .onStart { _uiState.value = UiState.Loading }
            .catch { cause -> _uiState.value = UiState.Error(cause.toString()) }
            .collectLatest { todoList ->
                if(todoList.isNotEmpty()) _uiState.value  = UiState.Success(todoList.toTodoVO())
                else _uiState.value = UiState.Empty
            }
    }

    fun deleteTodo(id : Long) = viewModelScope.launch {
        todoUseCases.deleteTodo(id = id)
    }

    fun splashInit() = viewModelScope.launch {
        launch {
            delay(2000)
            _splashState.value = true
        }
        fetchTodoList()
    }

}
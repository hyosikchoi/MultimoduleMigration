package com.hyosik.android.diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.diary.presentation.mapper.toTodoVO
import com.hyosik.android.diary.presentation.state.UiState
import com.hyosik.android.domain.usecase.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.UnInitialized)

    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    fun fetchTodoList() = viewModelScope.launch {
        todoUseCases.getTodoList()
            .onStart { _uiState.value = UiState.Loading }
            .catch { cause -> _uiState.value = UiState.Error(cause.toString()) }
            .collectLatest { todoList ->
                if(todoList.isNotEmpty()) _uiState.value  = UiState.Success(todoList.toTodoVO())
                else _uiState.value = UiState.Empty
            }
    }

    fun deleteTodo(id : Long) = viewModelScope.launch(Dispatchers.IO) {
        todoUseCases.deleteTodo(id = id)
    }


}
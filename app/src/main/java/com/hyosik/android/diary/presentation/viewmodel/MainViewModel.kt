package com.hyosik.android.diary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.domain.usecase.GetTodoListUsecase
import com.hyosik.android.diary.domain.usecase.InsertTodoUseCase
import com.hyosik.android.diary.presentation.mapper.toTodoModel
import com.hyosik.android.diary.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodoListUsecase: GetTodoListUsecase,
    private val insertTodoUseCase: InsertTodoUseCase
) : ViewModel() {

    private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.UnInitialized)

    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    fun fetchTodoList() = viewModelScope.launch {
        getTodoListUsecase()
            .onStart { _uiState.value = UiState.Loading }
            .catch { cause -> _uiState.value = UiState.Error(cause.toString()) }
            .collectLatest { todoList ->
                if(todoList.isNotEmpty()) _uiState.value  = UiState.Success(todoList.toTodoModel())
                else _uiState.value = UiState.Empty
            }
    }

    fun insertTodo(todoModel: TodoModel)  = viewModelScope.launch {
        insertTodoUseCase(todo = todoModel)
    }




}
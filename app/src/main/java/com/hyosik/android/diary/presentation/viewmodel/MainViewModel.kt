package com.hyosik.android.diary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.domain.usecase.DeleteTodoUseCase
import com.hyosik.android.diary.domain.usecase.GetTodoListUsecase
import com.hyosik.android.diary.domain.usecase.InsertTodoUseCase
import com.hyosik.android.diary.presentation.mapper.toTodoModel
import com.hyosik.android.diary.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodoListUsecase: GetTodoListUsecase,
    private val deleteTodoUseCase: DeleteTodoUseCase
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

    fun deleteTodo(id : Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteTodoUseCase(id = id)
    }


}
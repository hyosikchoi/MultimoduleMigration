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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    /** Room은 LiveData 혹은 Flow 타입의 형태를 return 받으면 알아서 IO Thread 로 수행하고 반환 한다.*/
    /** 그래서 Dispatchers 를 명시할 필요 없지만 그게 아니라면 아래와 같이 백그라운드 스레드를 명시해줘야 한다. */
    /** Ui Thread 에서 수행하면 error 가 발생한다. */
    fun insertTodo(todoModel: TodoModel)  = viewModelScope.launch(Dispatchers.IO) {
        insertTodoUseCase(todo = todoModel)
    }




}
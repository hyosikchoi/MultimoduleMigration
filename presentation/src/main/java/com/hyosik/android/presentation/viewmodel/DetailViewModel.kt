package com.hyosik.android.presentation.viewmodel

import com.hyosik.android.presentation.model.TodoVO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.domain.usecase.TodoUseCases
import com.hyosik.android.presentation.enum.DetailMode
import com.hyosik.android.presentation.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toTodoVO
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _detailUiState : MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.UnInitialized)

    val detailUiState  = _detailUiState.asStateFlow()

    private var detailMode = DetailMode.WRTIE

    fun fetchTodo(id : Long?)  = viewModelScope.launch {
        id?.let {
            todoUseCases.getTodo(it).onStart {
                _detailUiState.value = DetailUiState.Loading
            }.catch { cause -> _detailUiState.value = DetailUiState.Error(cause = cause.toString()) }
                .collectLatest { todo ->
                    detailMode = DetailMode.MODIFY
                    _detailUiState.value = DetailUiState.Success(todo = todo.toTodoVO())
                }
        } ?: kotlin.run {
            detailMode = DetailMode.WRTIE
            _detailUiState.value = DetailUiState.Empty
        }
    }

    /** Room은 LiveData 혹은 Flow 타입의 형태를 return 받으면 알아서 IO Thread 로 수행하고 반환 한다.*/
    /** 그래서 Dispatchers 를 명시할 필요 없지만 그게 아니라면 아래와 같이 백그라운드 스레드를 명시해줘야 한다. */
    /** Ui Thread 에서 수행하면 error 가 발생한다. */
    fun insertTodo(todoModel: TodoVO)  = viewModelScope.launch(Dispatchers.IO) {
        if(detailMode == DetailMode.WRTIE) todoUseCases.insertTodo(todo = todoModel)
        else todoUseCases.updateTodo(todo = todoModel)
    }

}
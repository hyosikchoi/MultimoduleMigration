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

    /** Room은 LiveData 혹은 Flow 타입의 형태를 return 받으면 cold stream 이므로 한번만 return value 가 아닌 */
    /** 지속적인 multiple return value 이므로 suspend 를 붙이면 안된다. */
    /** suspend 는 한번만 single return value 를 하기 때문이다. */
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

    /** flow 또는 livedata 가 아닌 경우는 Dispathcers.IO 를 직접 설정 안한다면 */
    /** suspend 함수로 만들어주면 room 에서 알아서 백그라운드 스레드에서 실행을 해준다. */
    /** suspend 를 빼고 UI Thread 에서 실행 한다면  */
    /** Cannot access database on the main thread since it may potentially lock the UI for a long period of time. 오류가 뜬다. */
    fun insertTodo(todoModel: TodoVO)  = viewModelScope.launch {
        if(detailMode == DetailMode.WRTIE) todoUseCases.insertTodo(todo = todoModel)
        else todoUseCases.updateTodo(todo = todoModel)
    }

}
package com.hyosik.android.presentation

import com.hyosik.android.domain.usecase.TodoUseCases
import com.hyosik.android.presentation.viewmodel.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelUnitTest {

    private lateinit var mainViewModel: MainViewModel

    @MockK
    private lateinit var todoUseCases: TodoUseCases

    private val dispathcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = dispathcher)
        MockKAnnotations.init(this, relaxed = true)
        mainViewModel = MainViewModel(todoUseCases = todoUseCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `mockk 이용한 viewmodel 호출 test`() {

        /** todoUseCase.getTodoList 호출 시 mock 객체 return */
        every { todoUseCases.getTodoList() } returns mockk()

        mainViewModel.fetchTodoList()

        /** todoUseCases.getTodoList 이 호출 되었는지 판단한다. */
        /** 호출 되었다면 success 호출하지 않았다면 failed 된다. */
        verify { todoUseCases.getTodoList() }

    }

}
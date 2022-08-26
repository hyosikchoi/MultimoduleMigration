package com.hyosik.android.diary.presentation.adapter

import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyosik.android.diary.presentation.state.DetailUiState
import com.hyosik.android.diary.presentation.state.UiState


@BindingAdapter("list")
fun RecyclerView.setList(
    state : UiState?
) {
    val adapter = adapter as? TodoAdapter

    state?.let {
        if(it is UiState.Success) adapter?.submitList(it.todoList)
    }
}

@BindingAdapter("android:visibility")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}


@BindingAdapter("title")
fun EditText.setTitle(
    state : DetailUiState?
) {
    state?.let {
        if(it is DetailUiState.Success) this.setText(it.todo.title)
    }
}


@BindingAdapter("description")
fun EditText.setDescription(
    state : DetailUiState?
) {
    state?.let {
        if(it is DetailUiState.Success) this.setText(it.todo.description)
    }
}
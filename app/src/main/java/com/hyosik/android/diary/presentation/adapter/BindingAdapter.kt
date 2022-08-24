package com.hyosik.android.diary.presentation.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
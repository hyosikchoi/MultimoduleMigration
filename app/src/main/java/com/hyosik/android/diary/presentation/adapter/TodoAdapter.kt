package com.hyosik.android.diary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.databinding.ItemTodoViewholderBinding

class TodoAdapter : ListAdapter<TodoModel,TodoAdapter.TodoViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoViewholderBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class TodoViewHolder(private val binding : ItemTodoViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoModel)  = with(binding) {
            todoModel = todo
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TodoModel>() {

            override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}
package com.hyosik.android.diary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.databinding.ItemTodoViewholderBinding
import com.hyosik.android.diary.presentation.CustomDialog

class TodoAdapter(private val itemClickListener : (TodoModel) -> Unit , private val deleteClickListener : (Long) -> Unit) : ListAdapter<TodoModel,TodoAdapter.TodoViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoViewholderBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class TodoViewHolder(private val binding : ItemTodoViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoModel) = with(binding) {
            todoModel = todo
            root.setOnClickListener {
                if(todo.lock) {
                   CustomDialog(
                       binding.root.context,
                       pwCallback = { pw ->
                           if(pw == todo.password) {
                               itemClickListener(todo)
                           } else {
                               Toast.makeText(binding.root.context , "비밀번호가 틀렸습니다." , Toast.LENGTH_SHORT).show()
                           }
                       }
                   ).show()
                } else {
                    itemClickListener(todo)
                }
            }
            deleteTodoButton.setOnClickListener {
                deleteClickListener(todo.id!!)
            }
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
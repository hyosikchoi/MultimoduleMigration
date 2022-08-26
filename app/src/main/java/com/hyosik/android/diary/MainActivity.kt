package com.hyosik.android.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.databinding.ActivityMainBinding
import com.hyosik.android.diary.presentation.DetailActivity
import com.hyosik.android.diary.presentation.adapter.TodoAdapter
import com.hyosik.android.diary.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    private val todoAdapter = TodoAdapter(itemClickListener = { todoModel ->
        val intent = Intent(this , DetailActivity::class.java)
        intent.putExtra(DetailActivity.DETAIL_ID,todoModel.id)
        startActivity(intent)
    },
        deleteClickListener = { id ->
            viewModel.deleteTodo(id = id)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.todoListRecyclerView.adapter = todoAdapter
        viewModel.fetchTodoList()

        binding.insertTodoButton.setOnClickListener {
            val intent = Intent(this , DetailActivity::class.java)
            startActivity(intent)
        }

    }
}
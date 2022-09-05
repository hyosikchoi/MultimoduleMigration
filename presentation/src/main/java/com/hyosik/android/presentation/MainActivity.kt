package com.hyosik.android.presentation

import com.hyosik.android.presentation.adapter.TodoAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyosik.android.presentation.databinding.ActivityMainBinding
import com.hyosik.android.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.thread

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

        binding = DataBindingUtil.setContentView(this@MainActivity , R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.todoListRecyclerView.adapter = todoAdapter
        viewModel.splashInit()

        binding.insertTodoButton.setOnClickListener {
            val intent = Intent(this@MainActivity , DetailActivity::class.java)
            startActivity(intent)
        }

    }
}
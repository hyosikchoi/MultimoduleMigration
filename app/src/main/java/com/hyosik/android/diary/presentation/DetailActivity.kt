package com.hyosik.android.diary.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.hyosik.android.diary.R
import com.hyosik.android.diary.data.local.model.TodoModel
import com.hyosik.android.diary.databinding.ActivityDetailBinding
import com.hyosik.android.diary.presentation.state.DetailUiState
import com.hyosik.android.diary.presentation.viewmodel.DetailViewModel
import com.hyosik.android.diary.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()

    private lateinit var binding : ActivityDetailBinding

    private var id : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.detailActivity = this
        id = when {
            intent.getLongExtra(DETAIL_ID, 100000) >= 100000 -> null
            else -> intent.getLongExtra(DETAIL_ID, 100000)
        }
        binding.detailViewModel = viewModel
        viewModel.fetchTodo(id = id)
    }

    fun insertTodo() {
        if(binding.titleEditTextView.text.isNotEmpty() && binding.descriptionEditTextView.text.isNotEmpty()) {
            viewModel.insertTodo(TodoModel(
                _id = id,
                _title = binding.titleEditTextView.text.toString(),
                _description = binding.descriptionEditTextView.text.toString(),
                _hasCompleted = false,
                _lock = false
            ))
            Toast.makeText(this,"저장되었습니다." , Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this,"타이틀과  내용을 적어주셔야합니다." , Toast.LENGTH_SHORT).show()
        }
    }

    fun finishActivity()  {
        finish()
    }

    companion object {
        const val DETAIL_ID = "ID"
    }

}
package com.hyosik.android.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyosik.android.presentation.databinding.ActivityDetailBinding
import com.hyosik.android.presentation.model.TodoVO
import com.hyosik.android.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()

    private lateinit var binding : ActivityDetailBinding

    private var id : Long? = null

    private var password : String = ""

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

        binding.lockCheckBox.setOnClickListener {
            if(binding.lockCheckBox.isChecked) {
                CustomDialog(
                 context = this,
                 pwCallback = { pwString ->
                     password = pwString
                 }
              ).show()
            } else {
                password = ""
            }
        }


    }

    fun insertTodo() {
        if(binding.titleEditTextView.text.isNotEmpty() && binding.descriptionEditTextView.text.isNotEmpty()) {
            viewModel.insertTodo(
                TodoVO(
                _id = id,
                _title = binding.titleEditTextView.text.toString(),
                _description = binding.descriptionEditTextView.text.toString(),
                _hasCompleted = false,
                _lock = binding.lockCheckBox.isChecked,
                _password = password
            )
            )
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
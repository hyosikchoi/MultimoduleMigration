package com.hyosik.android.diary.presentation

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.hyosik.android.diary.databinding.DialogPasswordBinding

class CustomDialog(
    context : Context,
    private val pwCallback : (String) -> Unit
) : Dialog(context) {

    private lateinit var binding : DialogPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() = with(binding) {

         setCancelable(false)

         // background 투명하게
         window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

         acceptButton.setOnClickListener {
             if(passwordEditText.text.isNotEmpty()) {
                 pwCallback(passwordEditText.text.toString())
                 dismiss()
             } else {
                 Toast.makeText(context, "비밀번호를 입력해주세요." ,Toast.LENGTH_SHORT).show()
             }
         }

    }

}
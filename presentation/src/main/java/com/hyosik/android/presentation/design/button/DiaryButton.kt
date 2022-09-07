package com.hyosik.android.presentation.design.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.hyosik.android.presentation.R

class DiaryButton(
    context: Context,
    attr : AttributeSet
) : AppCompatButton(context,attr) {

    init {
        setBackgroundColor(resources.getColor(R.color.blue_200,null))
        setTextColor(resources.getColor(R.color.white,null))
    }

}
package com.hyosik.android.presentation.design.layout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.hyosik.android.presentation.R

@SuppressLint("UseCompatLoadingForDrawables")
class ViewHolderLayout(
    context : Context,
    attrs : AttributeSet
) : ConstraintLayout(
    context, attrs
) {

    init {
        background = resources.getDrawable(R.drawable.viewholder_ripple,null)
    }

}
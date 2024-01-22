package com.aragones.sergio.randomusersapp.extensions

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

fun ImageView.getRoundImageView(radius: Float): RoundedBitmapDrawable {

    val imageBitmap = (drawable as BitmapDrawable).bitmap
    val imageDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
    imageDrawable.isCircular = true
    imageDrawable.cornerRadius = radius
    return imageDrawable
}
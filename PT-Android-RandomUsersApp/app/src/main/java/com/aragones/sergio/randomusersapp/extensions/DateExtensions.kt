package com.aragones.sergio.randomusersapp.extensions

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date?.toString(format: String? = null, language: String? = null): String? {

    val dateFormat = format ?: "yyyy-MM-dd"
    val locale = language?.let {
        Locale.forLanguageTag(it)
    } ?: run {
        Locale.getDefault()
    }
    this?.let {

        return try {
            SimpleDateFormat(dateFormat, locale).format(it)
        } catch (e: Exception) {

            Log.e("DateExtensions", e.message ?: "")
            null
        }
    } ?: run {

        Log.e("DateExtensions", "date null")
        return null
    }
}
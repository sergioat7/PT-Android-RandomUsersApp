package com.aragones.sergio.data

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String?.toDate(
    format: String? = null,
    language: String? = null,
    timeZone: TimeZone? = null
): Date? {

    val dateFormat = format ?: "yyyy-MM-dd"
    val locale = language?.let {
        Locale.forLanguageTag(it)
    } ?: run {
        Locale.getDefault()
    }
    val simpleDateFormat = SimpleDateFormat(dateFormat, locale)
    simpleDateFormat.timeZone = timeZone ?: TimeZone.getDefault()

    this?.let {

        return try {
            simpleDateFormat.parse(it)
        } catch (e: Exception) {

            Log.e("StringExtensions", e.message ?: "")
            null
        }
    } ?: run {
        Log.e("StringExtensions", "dateString null")
        return null
    }
}
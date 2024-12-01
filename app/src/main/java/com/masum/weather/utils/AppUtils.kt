package com.masum.weather.utils

import android.content.Context
import android.widget.Toast

object AppUtils {

    fun Context.showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
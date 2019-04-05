package com.rahul.sd2test.extras

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

object Constants {

    //http://sd2-hiring.herokuapp.com/api/users?offset=10&limit=10

    fun convertPixelsToDp(px: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

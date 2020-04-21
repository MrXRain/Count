package com.rain.myapplication

import android.util.ArrayMap
import android.util.SparseArray
import androidx.lifecycle.LifecycleOwner

/**
 * @author rain
 */
interface MainView:LifecycleOwner {
    fun showData(datas: ArrayMap<Int, MutableList<Float>>)
    fun error(error:String)
}
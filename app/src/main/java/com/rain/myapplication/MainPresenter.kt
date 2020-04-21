package com.rain.myapplication

import android.util.ArrayMap
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.rain.myapplication.api.Api
import kotlinx.coroutines.*

/**
 * @author rain
 */
class MainPresenter(@NonNull val view: MainView) : LifecycleObserver,
    CoroutineScope by MainScope() {

    private val datas: ArrayMap<Int, MutableList<Float>> by lazy {
        ArrayMap<Int, MutableList<Float>>()
    }

    init {
        view.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@Nullable owner: LifecycleOwner?) {
        cancel()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(@Nullable owner: LifecycleOwner?) {
        cancel()
    }

    fun request() {
        launch {
            if (datas.size > 1) {
                view.showData(datas)
                return@launch
            }

            val result = withContext(Dispatchers.IO) {
                Api.getCountDatas(1)
            }

            val list = mutableListOf<Float>()
            for (index in result!!.indices) {
                list.add(result[index].data)

                if (index < result.size - 1) {
                    if (!result[index].quarter.contains(
                            result[index + 1].quarter.split("-")[0],
                            true
                        )
                    ) {
                        val copyList = mutableListOf<Float>()
                        copyList.addAll(list)
                        datas[result[index].quarter.split("-")[0].toInt()] = copyList
                        list.clear()
                        continue
                    }
                } else {
                    datas[result[index].quarter.split("-")[0].toInt()] = list
                }
            }

            view.showData(datas)
        }
    }
}
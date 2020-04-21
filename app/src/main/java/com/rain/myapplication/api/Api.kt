package com.rain.myapplication.api

import android.util.SparseArray
import com.rain.myapplication.bean.Record
import com.rain.myapplication.api.base.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author rain
 */
object Api: Repository() {
    private const val BASE_URL:String = "https://data.gov.sg/"
    private const val COUNT = 1

    private val mServices:SparseArray<Any> by lazy {
        SparseArray<Any>()
    }

    var year:Int = 0
        set
    var endYaer:Int = 0
        set
    var quarter:Int = 1
        set
    var endQuarter:Int = 4
        set

    suspend fun getCountDatas(number:Int):List<Record>? {
        val result = safeCall {
            service().query(offset = safeCall {
                service().query(offset = number)
            }?.result?.total!!)
        }

        return result?.result?.records?.filter {
            if (it.quarter.isNotBlank()) {
                val time = it.quarter.split("-Q")
                when {
                    endYaer == 0 -> {
                        return@filter time[1].toInt() in quarter..endQuarter && time[0].toInt() >= year
                    }
                    endYaer > 0 && endYaer >= year -> {
                        return@filter time[1].toInt() in quarter..endQuarter && time[0].toInt() in year..endYaer
                    }
                    else -> {
                        return@filter false
                    }
                }
            } else {
                return@filter false
            }
        }
    }

    fun init() {
        initCountService()
    }

    private fun service():CountService {
        return mServices.get(COUNT) as CountService
    }

    private fun initCountService(){
        val mService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountService::class.java)

        mServices.put(COUNT,mService)
    }
}
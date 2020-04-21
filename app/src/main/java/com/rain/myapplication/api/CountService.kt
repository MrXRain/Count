package com.rain.myapplication.api

import com.rain.myapplication.bean.AckParam
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author rain
 */
interface CountService {

    @POST("api/action/datastore_search")
    suspend fun query(@Query("resource_id") id: String = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f", @Query("limit") offset: Int):Response<AckParam>
}
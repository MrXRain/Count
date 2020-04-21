package com.rain.myapplication.api.base

import android.util.Log
import com.rain.myapplication.api.exception.ApiException
import retrofit2.Response

/**
 * @author rain
 */
open class Repository {

    suspend fun <T : Any> safeCall(call: suspend () -> Response<T>): T? {
        val result = response(call)
        var data: T? = null
        when (result) {
            is Result.Success -> {
                data = result.data
            }

            is Result.Error -> {
                Log.e("Exception",result.exception.toString())
            }
        }

        return data
    }

    private suspend fun <T : Any> response(call: suspend () -> Response<T>): Result<T> {
        val result = call.invoke()

        return if (result.isSuccessful) {
            Result.Success(result.body()!!)
        } else {
            Result.Error(ApiException(result.errorBody()?.string()!!))
        }
    }

}
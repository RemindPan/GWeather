package com.tinyfight.gweather.data.network

import android.util.Log
import com.tinyfight.gweather.data.vo.VO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.network.NetworkHelper
 */

const val CODE_UNKNOWN = "UNKNOWN"
const val FAIL_MESSAGE = "MESSAGE_FAIL"

data class ApiError(
    val code: String? = null,
    val msg: String? = null,
)

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Fail(val error: ApiError?) : Result<Nothing>()
}

suspend inline fun <reified T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline apiCall: suspend () -> T?,
): Result<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            if (result != null) {
                Result.Success(result)
            } else {
                val error = ApiError(CODE_UNKNOWN, FAIL_MESSAGE)
                Result.Fail(error)
            }
        } catch (cancel: CancellationException) {
            Log.d("NetWorkError", "CancellationException ${cancel.message}")
            Result.Fail(ApiError(CODE_UNKNOWN, cancel.message))
        } catch (ex: HttpException) {
            Log.d("NetWorkError", "HttpException ${ex.message()}")
            Result.Fail(ApiError(ex.code().toString(), ex.message()))
        } catch (ex: Exception) {
            Log.d("NetWorkError", "Exception ${ex.message}")
            Result.Fail(ApiError(CODE_UNKNOWN, ex.message))
        }
    }
}

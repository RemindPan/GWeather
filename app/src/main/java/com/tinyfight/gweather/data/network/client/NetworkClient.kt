package com.tinyfight.gweather.data.network

import com.tinyfight.gweather.data.network.header.NetworkHeader
import com.tinyfight.gweather.data.network.header.NetworkHeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.network.NetworkApiFactory
 */
@Suppress("unused")
class NetworkClient private constructor(private val startArgs: StartArgs) {
    private var retrofit: Retrofit

    init {
        retrofit = buildRetrofit(buildOkhttpClient())
    }

    private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(startArgs.baseUrl ?: "")
            startArgs.callAdapterFactories.forEach { addCallAdapterFactory(it) }
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()
    }

    private fun buildOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            startArgs.header?.let {
                addInterceptor(NetworkHeaderInterceptor(it))
            }

            startArgs.interceptors.forEach {
                addInterceptor(it)
            }
        }.build()
    }

    class Builder {
        private val startArgs: StartArgs = StartArgs()

        fun setBaseUrl(baseUrl: String) = apply {
            startArgs.baseUrl = baseUrl
        }

        fun setHeader(header: NetworkHeader) = apply {
            startArgs.header = header
        }

        fun addInterceptor(interceptor: Interceptor) = apply {
            startArgs.interceptors.add(interceptor)
        }

        fun addCallAdapterFactory(factory: CallAdapter.Factory) = apply {
            startArgs.callAdapterFactories.add(factory)
        }

        fun build(): NetworkClient {
            return NetworkClient(startArgs)
        }
    }

    companion object {
        private var networkClient: NetworkClient? = null

        fun initialize(networkClient: NetworkClient) {
            Companion.networkClient = networkClient
        }

        fun <T> getApi(clazz: Class<T>): T {
            val retrofit = networkClient?.retrofit
            retrofit?.let {
                return it.create(clazz)
            } ?: kotlin.run {
                throw UninitializedPropertyAccessException()
            }
        }
    }
}

private data class StartArgs(
    var baseUrl: String? = null,
    var header: NetworkHeader? = null,
    val interceptors: ArrayList<Interceptor> = arrayListOf(),
    val callAdapterFactories: ArrayList<CallAdapter.Factory> = arrayListOf(),
)



package com.corellidev.data.retrofit

import com.corellidev.data.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiBuilder {

    companion object {
        const val CONNECTION_TIMEOUT_IN_SECONDS = 60L
        const val COVID_API_HOST = "https://api.covid19api.com/"
    }

    inline fun <reified T> createApi(url: String): T {
        val moshi = Moshi.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(createHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(T::class.java)
    }

    fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }
}
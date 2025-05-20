package org.sopt.collaboration.campuspick.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.collaboration.campuspick.BuildConfig
import org.sopt.collaboration.campuspick.data.interceptor.HeaderInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)

    object ServicePool {
        val campusPickService: CampusPickService by lazy {
            create<CampusPickService>()
        }
    }
}
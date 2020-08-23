package com.example.demoapp.api

import com.example.demoapp.data.Question
import com.example.demoapp.data.RepoDataSource
import com.example.demoapp.data.ResponseForm
import com.example.demoapp.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService : RepoDataSource {

    companion object {
        fun create(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("json/get/cpOAeecWGa?")
    suspend fun getForm(
        @Query("indent") id: String
    ): ResponseForm?


}
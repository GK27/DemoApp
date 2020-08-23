package com.example.demoapp.util

import com.example.demoapp.App
import com.example.demoapp.api.ApiService
import com.example.demoapp.api.RepoApiService
import com.example.demoapp.data.FormRepository

object ServiceLocator {
    private var apiService: ApiService? = null

    @Volatile
    var formRepository: FormRepository? = null

    fun provideReposRepository(context: App): FormRepository {
        synchronized(this) {
            return formRepository ?: formRepository ?: createReposRepository(context)
        }
    }

    private fun createReposRepository(context: App): FormRepository {
        val newRepository = FormRepository.getInstance(
            createRepoAPI()
        )
        formRepository = newRepository
        return newRepository
    }


    private fun createRepoAPI(): ApiService {
        val apiService = apiService ?: createApiService()
        return RepoApiService(ApiService = apiService)
    }

    private fun createApiService(): ApiService {
        return apiService ?: ApiService.create()

    }
}
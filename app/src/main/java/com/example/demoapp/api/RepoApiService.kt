package com.example.demoapp.api

import com.example.demoapp.data.Question
import com.example.demoapp.data.RepoDataSource
import com.example.demoapp.data.ResponseForm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoApiService internal constructor(
    private val ApiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoDataSource, ApiService {
    override suspend fun getForm(id: String): ResponseForm? {
        TODO("Not yet implemented")
    }

    override suspend fun getFromRepo(id: String): List<Question>? {
        return withContext(ioDispatcher) {
            ApiService.getForm(id)?.questions
        }
    }
}
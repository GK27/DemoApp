package com.example.demoapp.data

import android.util.Log
import com.example.demoapp.api.ApiService

class FormRepository(
    private val repoAPI: RepoDataSource
) {
    var quesRepos: List<Question>? = null

    suspend fun getFromRepo(forceRefresh: Boolean, index: String): List<Question>? {
        if (!forceRefresh && quesRepos != null) return quesRepos


        try {
            quesRepos = repoAPI.getFromRepo(index)

        } catch (ex: Exception) {
        }
        return quesRepos
    }

    companion object {
        @Volatile
        private var INSTANCE: FormRepository? = null
        fun getInstance(repoApiService: ApiService): FormRepository {
            return INSTANCE ?: FormRepository(repoApiService).apply { INSTANCE = this }
        }
    }
}
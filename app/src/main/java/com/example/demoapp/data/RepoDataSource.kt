package com.example.demoapp.data

import com.example.demoapp.data.Question

interface RepoDataSource {

    suspend fun getFromRepo(query: String): List<Question>?

}
package com.example.demoapp

import android.app.Application
import com.example.demoapp.data.FormRepository
import com.example.demoapp.util.ServiceLocator

class App : Application() {
    val formRepository: FormRepository
        get() = ServiceLocator.provideReposRepository(this)

}
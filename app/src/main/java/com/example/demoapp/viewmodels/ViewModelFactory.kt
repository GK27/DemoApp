package com.example.demoapp.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.App
import com.example.demoapp.data.FormRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val formRepository: FormRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FormViewModel::class.java) ->
                    FormViewModel(formRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as App).formRepository
    return ViewModelFactory(repository)
}

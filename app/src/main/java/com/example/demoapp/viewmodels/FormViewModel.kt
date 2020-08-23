package com.example.demoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.FormRepository
import com.example.demoapp.data.Question
import kotlinx.coroutines.launch

class FormViewModel(private val formRepository: FormRepository) : ViewModel() {
    private val _queo = MutableLiveData<List<Question>>()
    val reQue: LiveData<List<Question>> = _queo
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success


    fun fetchReposForm(forceRefresh: Boolean, index: String) {
        viewModelScope.launch {
            val result = formRepository.getFromRepo(forceRefresh,index)
            if (result != null) {
                _queo.postValue(result)
                _success.postValue(true)

            } else {
                _error.postValue("Could not get repositories!")
            }
        }
    }
}
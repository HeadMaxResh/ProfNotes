package com.example.profnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.data.api.Api
import com.example.profnotes.domain.model.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val coursesItemFlow: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())

    init {
        val api = Api.apiService
        viewModelScope.launch {
            val courses = api.getCourses().data.toList()
            coursesItemFlow.emit(courses)
        }
    }
}
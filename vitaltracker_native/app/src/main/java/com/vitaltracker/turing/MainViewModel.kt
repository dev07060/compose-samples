package com.vitaltracker.turing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // State flow to hold the view state
    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    // Example function to start a process
    fun startProcess() {
        viewModelScope.launch {
            // Simulate loading state
            _viewState.value = _viewState.value.copy(isLoading = true)

            // Simulate some asynchronous process
            // e.g., make a network request, perform a computation, etc.

            // Once the process is complete, update the state
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }
}

data class MainViewState(val isLoading: Boolean = false)

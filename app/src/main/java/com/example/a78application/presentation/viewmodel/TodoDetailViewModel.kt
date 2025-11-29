package com.example.a78application.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a78application.domain.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoDetailViewModel : ViewModel() {
    private val _selectedTodo = MutableStateFlow<TodoItem?>(null)
    val selectedTodo: StateFlow<TodoItem?> = _selectedTodo.asStateFlow()

    fun setTodo(todo: TodoItem) {
        _selectedTodo.value = todo
    }
}
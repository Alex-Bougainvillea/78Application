package com.example.a78application.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a78application.domain.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoListViewModel : ViewModel() {
    private val _todosState = MutableStateFlow<List<TodoItem>>(emptyList())
    val todosState: StateFlow<List<TodoItem>> = _todosState.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            // Временные данные для теста
            _todosState.value = listOf(
                TodoItem(1, "Тестовая задача 1", "Описание 1", false),
                TodoItem(2, "Тестовая задача 2", "Описание 2", true),
                TodoItem(3, "Тестовая задача 3", "Описание 3", false)
            )
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            _todosState.value = _todosState.value.map { todo ->
                if (todo.id == id) {
                    todo.copy(isCompleted = !todo.isCompleted)
                } else {
                    todo
                }
            }
        }
    }
}
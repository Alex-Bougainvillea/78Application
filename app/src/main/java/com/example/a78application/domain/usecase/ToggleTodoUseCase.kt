package com.example.a78application.domain.usecase

import com.example.a78application.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        repository.toggleTodo(id)
    }
}
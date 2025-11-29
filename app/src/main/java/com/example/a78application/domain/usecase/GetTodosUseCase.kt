package com.example.a78application.domain.usecase

import com.example.a78application.domain.model.TodoItem
import com.example.a78application.domain.repository.TodoRepository

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> = repository.getTodos()
}
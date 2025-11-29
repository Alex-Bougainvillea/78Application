package com.example.a78application.domain.repository

import com.example.a78application.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}

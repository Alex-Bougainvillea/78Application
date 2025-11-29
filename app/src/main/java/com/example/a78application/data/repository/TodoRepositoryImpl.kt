package com.example.a78application.data.repository

import com.example.a78application.data.local.TodoJsonDataSource
import com.example.a78application.domain.model.TodoItem
import com.example.a78application.domain.repository.TodoRepository

class TodoRepositoryImpl(private val dataSource: TodoJsonDataSource) : TodoRepository {
    private var currentTodos: List<TodoItem> = emptyList()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        currentTodos = dataSource.getTodos().map { dto ->
            TodoItem(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                isCompleted = dto.isCompleted
            )
        }
    }

    override suspend fun getTodos(): List<TodoItem> {
        return currentTodos
    }

    override suspend fun toggleTodo(id: Int) {
        currentTodos = currentTodos.map { todo ->
            if (todo.id == id) {
                todo.copy(isCompleted = !todo.isCompleted)
            } else {
                todo
            }
        }
    }
}
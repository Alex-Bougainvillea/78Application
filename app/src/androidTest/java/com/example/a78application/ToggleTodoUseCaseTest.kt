package com.example.a78application

import com.example.a78application.domain.repository.TodoRepository
import com.example.a78application.domain.usecase.ToggleTodoUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleTodoUseCaseTest {

    private val mockRepository: TodoRepository = mockk()
    private val toggleTodoUseCase = ToggleTodoUseCase(mockRepository)

    @Test
    fun toggleTodo() = runTest {
        // Arrange
        val testId = 1
        coEvery { mockRepository.toggleTodo(testId) } returns Unit

        // Act
        toggleTodoUseCase(testId)

        // Assert
        coVerify { mockRepository.toggleTodo(testId) }
    }
}
package com.example.a78application

import com.example.a78application.domain.model.TodoItem
import com.example.a78application.domain.repository.TodoRepository
import com.example.a78application.domain.usecase.GetTodosUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTodosUseCaseTest {

    private val mockRepository: TodoRepository = mockk()
    private val getTodosUseCase = GetTodosUseCase(mockRepository)

    @Test
    fun GetTodosUseCase() = runTest {
        // Arrange
        val expectedTasks = listOf(
            TodoItem(1, "Task 1", "Description 1", false),
            TodoItem(2, "Task 2", "Description 2", true),
            TodoItem(3, "Task 3", "Description 3", false)
        )

        coEvery { mockRepository.getTodos() } returns expectedTasks

        // Act
        val result = getTodosUseCase()

        // Assert
        assertEquals(3, result.size)
        assertEquals(expectedTasks, result)
    }
}
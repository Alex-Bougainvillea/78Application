package com.example.a78application

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a78application.presentation.ui.screen.TodoListScreen
import com.example.a78application.presentation.viewmodel.TodoListViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkboxToggles() {
        // Arrange
        val mockViewModel: TodoListViewModel = mockk()
        val testTasks = listOf(
            com.example.a78application.domain.model.TodoItem(1, "Test Task", "Test Description", false)
        )

        val tasksFlow = MutableStateFlow(testTasks)
        every { mockViewModel.todosState } returns tasksFlow
        every { mockViewModel.toggleTodo(1) } returns Unit

        // Act
        composeTestRule.setContent {
            TodoListScreen(
                viewModel = mockViewModel,
                onTodoClick = {}
            )
        }

        // Находим и кликаем на чекбокс (используем contentDescription)
        composeTestRule.onNodeWithContentDescription("Toggle task 1")
            .performClick()

        // Assert
        verify { mockViewModel.toggleTodo(1) }
    }
}
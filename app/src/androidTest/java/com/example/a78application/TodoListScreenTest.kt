package com.example.a78application

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a78application.presentation.ui.screen.TodoListScreen
import com.example.a78application.presentation.viewmodel.TodoListViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel: TodoListViewModel = mockk()

    @Test
    fun displayAll() {
        // Arrange
        val testTasks = listOf(
            com.example.a78application.domain.model.TodoItem(1, "Купить молоко", "2 литра, обезжиренное", false),
            com.example.a78application.domain.model.TodoItem(2, "Позвонить маме", "Спросить про выходные", true),
            com.example.a78application.domain.model.TodoItem(3, "Сделать ДЗ по Android", "Clean Architecture + Compose", false)
        )

        every { mockViewModel.todosState } returns kotlinx.coroutines.flow.MutableStateFlow(testTasks)

        // Act
        composeTestRule.setContent {
            TodoListScreen(
                viewModel = mockViewModel,
                onTodoClick = {}
            )
        }

        // Assert
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
        composeTestRule.onNodeWithText("Позвонить маме").assertExists()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertExists()

        // Проверяем что всего 3 задачи
        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()
    }
}
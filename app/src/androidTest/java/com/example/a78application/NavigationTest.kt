package com.example.a78application

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a78application.domain.model.TodoItem
import com.example.a78application.presentation.viewmodel.TodoListViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigationFromListToDetailAndBack() {
        // Arrange - создаем моки ViewModel и тестовые данные
        val mockListViewModel: TodoListViewModel = mockk()
        val testTasks = listOf(
            TodoItem(1, "Купить молоко", "2 литра, обезжиренное", false),
            TodoItem(2, "Позвонить маме", "Спросить про выходные", true),
            TodoItem(3, "Сделать ДЗ по Android", "Clean Architecture + Compose", false)
        )

        every { mockListViewModel.todosState } returns MutableStateFlow(testTasks)

        // TODO: Нужно будет настроить реальную навигацию в NavGraph

        // Act Assert
        composeTestRule.setContent {
            var showDetailScreen by remember { mutableStateOf(false) }
            var selectedTodo by remember { mutableStateOf<TodoItem?>(null) }

            if (showDetailScreen && selectedTodo != null) {
                // Экран деталей задачи
                androidx.compose.material3.Text("Детали задачи")
                androidx.compose.material3.Text("Заголовок: ${selectedTodo!!.title}")
                androidx.compose.material3.Text("Описание: ${selectedTodo!!.description}")
                androidx.compose.material3.Text("Статус: ${if (selectedTodo!!.isCompleted) "Выполнена" else "Не выполнена"}")
                androidx.compose.material3.Button(
                    onClick = { showDetailScreen = false }
                ) {
                    androidx.compose.material3.Text("Назад")
                }
            } else {
                // Экран списка задач
                com.example.a78application.presentation.ui.screen.TodoListScreen(
                    viewModel = mockListViewModel,
                    onTodoClick = { todo ->
                        selectedTodo = todo
                        showDetailScreen = true
                    }
                )
            }
        }

        // Given - главный экран со списком задач
        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()

        // When - кликаем на задачу "Купить молоко"
        composeTestRule.onNodeWithText("Купить молоко").performClick()

        // Then - переходим на экран деталей
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithText("Детали задачи").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Детали задачи").assertIsDisplayed()
        composeTestRule.onNodeWithText("Заголовок: Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Описание: 2 литра, обезжиренное").assertIsDisplayed()
        composeTestRule.onNodeWithText("Статус: Не выполнена").assertIsDisplayed()

        // When - нажимаем кнопку "Назад"
        composeTestRule.onNodeWithText("Назад").performClick()

        // Then - возвращаемся на главный экран
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithText("Купить молоко").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()
    }
}
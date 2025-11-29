package com.example.a78application.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a78application.domain.model.TodoItem
import com.example.a78application.presentation.ui.screen.TodoListScreen
import com.example.a78application.presentation.ui.screen.TodoDetailScreen
import com.example.a78application.presentation.viewmodel.TodoListViewModel
import com.example.a78application.presentation.viewmodel.TodoDetailViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val viewModel: TodoListViewModel = viewModel()
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { todo ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("selectedTodo", todo)
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            val viewModel: TodoDetailViewModel = viewModel()
            val todo = navController.previousBackStackEntry?.savedStateHandle?.get<TodoItem>("selectedTodo")

            LaunchedEffect(todo) {
                if (todo != null) {
                    viewModel.setTodo(todo)
                }
            }

            TodoDetailScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
package com.example.a78application.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a78application.domain.model.TodoItem
import com.example.a78application.presentation.ui.component.TodoItemComponent
import com.example.a78application.presentation.viewmodel.TodoListViewModel



@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onTodoClick: (TodoItem) -> Unit
) {
    val todos by viewModel.todosState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Список задач",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(todos) { todo ->
                TodoItemComponent(
                    todo = todo,
                    onItemClick = onTodoClick,
                    onCheckedChange = { checked ->
                        if (checked != todo.isCompleted) {
                            viewModel.toggleTodo(todo.id)
                        }
                    }
                )
            }
        }
    }
}
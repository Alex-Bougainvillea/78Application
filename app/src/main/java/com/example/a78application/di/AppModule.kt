package com.example.a78application.di

import android.content.Context
import com.example.a78application.data.local.TodoJsonDataSource
import com.example.a78application.data.repository.TodoRepositoryImpl
import com.example.a78application.domain.repository.TodoRepository
import com.example.a78application.domain.usecase.GetTodosUseCase
import com.example.a78application.domain.usecase.ToggleTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoJsonDataSource(@ApplicationContext context: Context): TodoJsonDataSource {
        return TodoJsonDataSource(context)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(dataSource: TodoJsonDataSource): TodoRepository {
        return TodoRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleTodoUseCase(repository: TodoRepository): ToggleTodoUseCase {
        return ToggleTodoUseCase(repository)
    }
}
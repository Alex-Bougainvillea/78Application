package com.example.a78application.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) : Parcelable
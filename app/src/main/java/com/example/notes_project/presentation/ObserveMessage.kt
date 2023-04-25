package com.example.notes_project.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ObserveMessage {
    suspend operator fun invoke(): String
}
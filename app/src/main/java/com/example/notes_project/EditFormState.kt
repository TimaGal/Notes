package com.example.notes_project

import android.graphics.Color
import com.example.notes_project.useCases.ChooseColorUseCase
import com.example.notes_project.useCases.ChooseStyleUseCase

data class EditFormState(val chooseColorUseCase: ChooseColorUseCase, val chooseStyleUseCase: ChooseStyleUseCase)

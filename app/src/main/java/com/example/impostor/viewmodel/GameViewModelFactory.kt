package com.example.impostor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.impostor.data.PlayerPreferences

class GameViewModelFactory(
    private val playerPreferences: PlayerPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(playerPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

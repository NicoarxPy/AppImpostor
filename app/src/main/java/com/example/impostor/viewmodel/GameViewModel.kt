package com.example.impostor.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impostor.data.Category
import com.example.impostor.data.GameState
import com.example.impostor.data.Player
import com.example.impostor.data.PlayerPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val playerPreferences: PlayerPreferences? = null
) : ViewModel() {
    var selectedCategory by mutableStateOf<Category?>(null)
        private set

    var players by mutableStateOf<List<Player>>(emptyList())
        private set

    var gameState by mutableStateOf<GameState?>(null)
        private set

    private val _savedPlayerNames = MutableStateFlow<List<String>>(emptyList())
    val savedPlayerNames: StateFlow<List<String>> = _savedPlayerNames.asStateFlow()

    init {
        playerPreferences?.let { prefs ->
            viewModelScope.launch {
                prefs.savedPlayers.collect { saved ->
                    _savedPlayerNames.value = saved
                }
            }
        }
    }

    fun selectCategory(category: Category) {
        selectedCategory = category
    }

    fun addPlayers(playerNames: List<String>) {
        players = playerNames.map { Player(it) }
        // Guardar los nombres
        viewModelScope.launch {
            playerPreferences?.savePlayers(playerNames)
        }
    }

    fun clearSavedPlayers() {
        viewModelScope.launch {
            playerPreferences?.clearPlayers()
        }
    }

    fun startGame(randomStart: Boolean = false, votingEnabled: Boolean = false) {
        val category = selectedCategory ?: return
        val selectedWord = category.words.random()
        val impostorIndex = players.indices.random()
        
        // Resetear el estado de eliminación
        val resetPlayers = players.map { it.copy(isEliminated = false, hasSeenCard = false) }
        players = resetPlayers
        
        gameState = GameState(
            category = category,
            players = resetPlayers,
            selectedWord = selectedWord,
            impostorIndex = impostorIndex,
            randomStart = randomStart,
            votingEnabled = votingEnabled
        )
    }

    fun setStarter(starterIndex: Int) {
        gameState?.let { state ->
            gameState = state.copy(currentStarterIndex = starterIndex)
        }
    }

    fun markPlayerAsViewed(playerIndex: Int) {
        gameState?.let { state ->
            val updatedPlayers = state.players.toMutableList()
            updatedPlayers[playerIndex] = updatedPlayers[playerIndex].copy(hasSeenCard = true)
            gameState = state.copy(players = updatedPlayers)
        }
    }

    fun resetGame() {
        val category = selectedCategory ?: return
        val randomStart = gameState?.randomStart ?: false
        val votingEnabled = gameState?.votingEnabled ?: false
        
        // Generar nueva palabra e impostor aleatorio
        val selectedWord = category.words.random()
        val impostorIndex = players.indices.random()
        
        // Resetear el estado de vistos y eliminados
        val resetPlayers = players.map { it.copy(hasSeenCard = false, isEliminated = false) }
        players = resetPlayers
        
        // Actualizar el estado con los nuevos valores
        gameState = GameState(
            category = category,
            players = resetPlayers,
            selectedWord = selectedWord,
            impostorIndex = impostorIndex,
            randomStart = randomStart,
            currentStarterIndex = null,
            votingEnabled = votingEnabled
        )
    }
    
    fun eliminatePlayer(playerIndex: Int) {
        gameState?.let { state ->
            val updatedPlayers = state.players.toMutableList()
            updatedPlayers[playerIndex] = updatedPlayers[playerIndex].copy(isEliminated = true)
            gameState = state.copy(players = updatedPlayers)
        }
    }

    fun resetAll() {
        selectedCategory = null
        players = emptyList()
        gameState = null
    }
}

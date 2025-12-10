package com.example.impostor.data

data class Player(
    val name: String,
    var hasSeenCard: Boolean = false,
    var isEliminated: Boolean = false
)

data class GameState(
    val category: Category,
    val players: List<Player>,
    val selectedWord: String,
    val impostorIndex: Int,
    val randomStart: Boolean = false,
    val currentStarterIndex: Int? = null,
    val votingEnabled: Boolean = false
)

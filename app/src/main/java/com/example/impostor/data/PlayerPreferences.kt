package com.example.impostor.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "player_preferences")

class PlayerPreferences(private val context: Context) {
    
    companion object {
        private val SAVED_PLAYERS_KEY = stringPreferencesKey("saved_players")
    }
    
    val savedPlayers: Flow<List<String>> = context.dataStore.data
        .map { preferences ->
            val playersString = preferences[SAVED_PLAYERS_KEY] ?: ""
            if (playersString.isEmpty()) {
                emptyList()
            } else {
                playersString.split(",")
            }
        }
    
    suspend fun savePlayers(players: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[SAVED_PLAYERS_KEY] = players.joinToString(",")
        }
    }
    
    suspend fun clearPlayers() {
        context.dataStore.edit { preferences ->
            preferences.remove(SAVED_PLAYERS_KEY)
        }
    }
}

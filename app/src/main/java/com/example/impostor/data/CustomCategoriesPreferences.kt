package com.example.impostor.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

private val Context.customCategoriesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "custom_categories"
)

@Serializable
data class CustomCategory(
    val id: String,
    val name: String,
    val words: List<String>
)

class CustomCategoriesPreferences(private val context: Context) {
    private val CUSTOM_CATEGORIES_KEY = stringPreferencesKey("custom_categories")
    
    val customCategories: Flow<List<CustomCategory>> = context.customCategoriesDataStore.data
        .map { preferences ->
            val jsonString = preferences[CUSTOM_CATEGORIES_KEY] ?: "[]"
            try {
                Json.decodeFromString<List<CustomCategory>>(jsonString)
            } catch (e: Exception) {
                emptyList()
            }
        }
    
    suspend fun saveCategory(category: CustomCategory) {
        context.customCategoriesDataStore.edit { preferences ->
            val currentJson = preferences[CUSTOM_CATEGORIES_KEY] ?: "[]"
            val currentCategories = try {
                Json.decodeFromString<List<CustomCategory>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedCategories = currentCategories + category
            preferences[CUSTOM_CATEGORIES_KEY] = Json.encodeToString(updatedCategories)
        }
    }
    
    suspend fun updateCategory(category: CustomCategory) {
        context.customCategoriesDataStore.edit { preferences ->
            val currentJson = preferences[CUSTOM_CATEGORIES_KEY] ?: "[]"
            val currentCategories = try {
                Json.decodeFromString<List<CustomCategory>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedCategories = currentCategories.map { 
                if (it.id == category.id) category else it 
            }
            preferences[CUSTOM_CATEGORIES_KEY] = Json.encodeToString(updatedCategories)
        }
    }
    
    suspend fun deleteCategory(categoryId: String) {
        context.customCategoriesDataStore.edit { preferences ->
            val currentJson = preferences[CUSTOM_CATEGORIES_KEY] ?: "[]"
            val currentCategories = try {
                Json.decodeFromString<List<CustomCategory>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedCategories = currentCategories.filter { it.id != categoryId }
            preferences[CUSTOM_CATEGORIES_KEY] = Json.encodeToString(updatedCategories)
        }
    }
    
    suspend fun clearAll() {
        context.customCategoriesDataStore.edit { preferences ->
            preferences[CUSTOM_CATEGORIES_KEY] = "[]"
        }
    }
}

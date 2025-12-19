package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.data.Category
import com.example.impostor.ui.components.AutoSizeText
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategoryScreen(
    onBackClick: () -> Unit,
    onCategoryCreated: (String, String, List<String>) -> Unit,
    existingCategory: Category? = null
) {
    var categoryName by remember { mutableStateOf(existingCategory?.name ?: "") }
    var wordsText by remember { mutableStateOf("") }
    var addedWords by remember { mutableStateOf<List<String>>(existingCategory?.words ?: emptyList()) }
    var editingWordIndex by remember { mutableStateOf<Int?>(null) }
    var editingWordText by remember { mutableStateOf("") }
    var showNameError by remember { mutableStateOf(false) }
    var showWordsError by remember { mutableStateOf(false) }
    
    Scaffold(
        containerColor = Color(0xFF212121), // Fondo gris oscuro
        topBar = {
            TopAppBar(
                title = { 
                    AutoSizeText(
                        if (existingCategory != null) "Editar categoría" else "Nueva categoría",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Volver", tint = Color.White)
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            if (categoryName.trim().isEmpty()) {
                                showNameError = true
                            } else if (addedWords.isEmpty()) {
                                showWordsError = true
                            } else {
                                val id = existingCategory?.id ?: UUID.randomUUID().toString()
                                onCategoryCreated(id, categoryName.trim(), addedWords)
                            }
                        },
                        modifier = Modifier.padding(end = 8.dp).width(100.dp),
                        enabled = categoryName.trim().isNotEmpty() && addedWords.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                            disabledContentColor = Color.White.copy(alpha = 0.5f)
                        )
                    ) {
                        AutoSizeText(if (existingCategory != null) "Guardar" else "Crear")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF212121),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            item {
                AutoSizeText(
                    text = "Nombre de la categoría",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White
                )
                
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { 
                        categoryName = it
                        showNameError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ej: Países, Marcas, etc.", color = Color.White.copy(alpha = 0.5f)) },
                    isError = showNameError,
                    supportingText = if (showNameError) {
                        { AutoSizeText("El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error) }
                    } else null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
                    )
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                AutoSizeText(
                    text = "Agregar palabras",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White
                )
                
                Text(
                    text = "Escribe una palabra por línea. Puedes pegar un texto con múltiples palabras.",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                OutlinedTextField(
                    value = wordsText,
                    onValueChange = { 
                        wordsText = it
                        showWordsError = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    placeholder = { Text("Palabra 1\nPalabra 2\nPalabra 3...", color = Color.White.copy(alpha = 0.5f)) },
                    isError = showWordsError,
                    supportingText = if (showWordsError) {
                        { AutoSizeText("Debes agregar al menos una palabra", color = MaterialTheme.colorScheme.error) }
                    } else null,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = {
                        val words = wordsText.lines()
                            .map { it.trim() }
                            .filter { it.isNotEmpty() }
                        
                        if (words.isNotEmpty()) {
                            addedWords = addedWords + words
                            wordsText = ""
                            showWordsError = false
                        } else {
                            showWordsError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    AutoSizeText("Agregar palabras")
                }
                
                if (addedWords.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    AutoSizeText(
                        text = "Palabras agregadas (${addedWords.size})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 12.dp),
                        color = Color.White
                    )
                }
            }
            
            itemsIndexed(addedWords) { index, word ->
                if (editingWordIndex == index) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black // Tarjeta negra
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = editingWordText,
                                onValueChange = { editingWordText = it },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedContainerColor = Color(0xFF212121), // Un poco más claro dentro de tarjeta negra
                                    unfocusedContainerColor = Color(0xFF212121),
                                    cursorColor = Color.White,
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
                                )
                            )
                            IconButton(
                                onClick = {
                                    if (editingWordText.trim().isNotEmpty()) {
                                        addedWords = addedWords.toMutableList().apply {
                                            set(index, editingWordText.trim())
                                        }
                                    }
                                    editingWordIndex = null
                                    editingWordText = ""
                                }
                            ) {
                                Icon(Icons.Default.Close, "Guardar", tint = Color.White)
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black // Tarjeta negra
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AutoSizeText(
                                text = word,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f),
                                color = Color.White
                            )
                            Row {
                                IconButton(
                                    onClick = {
                                        editingWordIndex = index
                                        editingWordText = word
                                    },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Edit,
                                        "Editar",
                                        tint = Color.White.copy(alpha = 0.7f),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        addedWords = addedWords.filterIndexed { i, _ -> i != index }
                                    },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        "Eliminar",
                                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

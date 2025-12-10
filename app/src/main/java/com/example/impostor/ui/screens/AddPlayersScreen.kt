package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlayersScreen(
    categoryName: String,
    savedPlayers: List<String>,
    onBackClick: () -> Unit,
    onStartGame: (List<String>, Boolean) -> Unit,
    onClearSavedPlayers: () -> Unit
) {
    var playerCount by remember { mutableStateOf(savedPlayers.size.coerceAtLeast(3)) }
    var playerNames by remember(savedPlayers) {
        mutableStateOf(
            if (savedPlayers.isNotEmpty()) {
                savedPlayers.toMutableList()
            } else {
                List(3) { "" }
            }
        )
    }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var randomStart by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }

    LaunchedEffect(playerCount) {
        if (playerCount > playerNames.size) {
            playerNames = playerNames + List(playerCount - playerNames.size) { "" }
        } else if (playerCount < playerNames.size) {
            playerNames = playerNames.take(playerCount)
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Borrar participantes guardados") },
            text = { Text("¿Estás seguro de que quieres borrar todos los participantes guardados?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClearSavedPlayers()
                        playerNames = List(playerCount) { "" }
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Borrar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = { Text("Inicio aleatorio") },
            text = { Text("En cada ronda se elegirá de manera aleatoria quién debe comenzar la discusión mediante una ruleta interactiva.") },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text("Entendido")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = categoryName,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    if (savedPlayers.isNotEmpty()) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Borrar participantes guardados",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (savedPlayers.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(
                        text = "✓ Jugadores guardados cargados",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            Text(
                text = "¿Cuántos jugadores?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (playerCount > 2) {
                            playerCount--
                        }
                    },
                    enabled = playerCount > 2
                ) {
                    Text("-", fontSize = 24.sp)
                }

                Text(
                    text = "$playerCount jugadores",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = {
                        if (playerCount < 20) {
                            playerCount++
                        }
                    },
                    enabled = playerCount < 20
                ) {
                    Text("+", fontSize = 24.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Nombres de los jugadores:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Checkbox(
                                    checked = randomStart,
                                    onCheckedChange = { randomStart = it }
                                )
                                Text(
                                    text = "Inicio aleatorio",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            IconButton(onClick = { showInfoDialog = true }) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = "Información",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                itemsIndexed(playerNames) { index, name ->
                    OutlinedTextField(
                        value = name,
                        onValueChange = { newName ->
                            playerNames = playerNames.toMutableList().also {
                                it[index] = newName
                            }
                        },
                        label = { Text("Jugador ${index + 1}") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val validNames = playerNames.filter { it.isNotBlank() }
                    if (validNames.size >= 2) {
                        onStartGame(validNames, randomStart)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = playerNames.count { it.isNotBlank() } >= 2
            ) {
                Text(
                    text = "Comenzar Juego",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

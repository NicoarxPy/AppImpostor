package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
    onStartGame: (List<String>, Boolean, Boolean) -> Unit,
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
    var votingEnabled by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    var showVotingInfoDialog by remember { mutableStateOf(false) }

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
    
    if (showVotingInfoDialog) {
        AlertDialog(
            onDismissRequest = { showVotingInfoDialog = false },
            title = { Text("Votaciones") },
            text = { Text("Los jugadores votarán para identificar al impostor. Si se equivocan, el jugador votado es eliminado y continúa la ronda. Gana el impostor si solo quedan 2 jugadores vivos.") },
            confirmButton = {
                TextButton(onClick = { showVotingInfoDialog = false }) {
                    Text("Entendido")
                }
            }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 48.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Categoría: $categoryName",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Jugadores",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                if (savedPlayers.isNotEmpty()) {
                    IconButton(
                        onClick = { showDeleteDialog = true },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Borrar guardados",
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                // Player count selector
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    ),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (playerCount > 2) playerCount-- },
                            enabled = playerCount > 2,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(
                                text = "−",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Light
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$playerCount",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "jugadores",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }

                        IconButton(
                            onClick = { if (playerCount < 20) playerCount++ },
                            enabled = playerCount < 20,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(
                                text = "+",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                            ),
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
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
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                }
                                IconButton(onClick = { showInfoDialog = true }) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Información",
                                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                            ),
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Checkbox(
                                        checked = votingEnabled,
                                        onCheckedChange = { votingEnabled = it }
                                    )
                                    Text(
                                        text = "Habilitar votaciones",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                }
                                IconButton(onClick = { showVotingInfoDialog = true }) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Información",
                                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
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
                            label = { 
                                Text(
                                    "Jugador ${index + 1}",
                                    fontWeight = FontWeight.Light
                                ) 
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Words
                            ),
                            shape = MaterialTheme.shapes.medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val validNames = playerNames.filter { it.isNotBlank() }
                        if (validNames.size >= 2) {
                            onStartGame(validNames, randomStart, votingEnabled)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    enabled = playerNames.count { it.isNotBlank() } >= 2,
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Comenzar Juego",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

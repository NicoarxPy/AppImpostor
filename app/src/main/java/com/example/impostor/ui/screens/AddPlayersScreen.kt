package com.example.impostor.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.ui.components.AutoSizeText

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
            title = { AutoSizeText("Borrar participantes guardados", color = Color.White) },
            text = { Text("¿Estás seguro de que quieres borrar todos los participantes guardados?", color = Color.White.copy(alpha = 0.8f)) },
            containerColor = Color(0xFF212121),
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
                    AutoSizeText("Borrar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    AutoSizeText("Cancelar")
                }
            }
        )
    }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = { AutoSizeText("Inicio aleatorio", color = Color.White) },
            text = { Text("En cada ronda se elegirá de manera aleatoria quién debe comenzar la discusión mediante una ruleta interactiva.", color = Color.White.copy(alpha = 0.8f)) },
            containerColor = Color(0xFF212121),
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    AutoSizeText("Entendido")
                }
            }
        )
    }
    
    if (showVotingInfoDialog) {
        AlertDialog(
            onDismissRequest = { showVotingInfoDialog = false },
            title = { AutoSizeText("Votaciones", color = Color.White) },
            text = { Text("Los jugadores votarán para identificar al impostor. Si se equivocan, el jugador votado es eliminado y continúa la ronda. Gana el impostor si solo quedan 2 jugadores vivos.", color = Color.White.copy(alpha = 0.8f)) },
            containerColor = Color(0xFF212121),
            confirmButton = {
                TextButton(onClick = { showVotingInfoDialog = false }) {
                    AutoSizeText("Entendido")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121))
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
                    AutoSizeText(
                        text = "Categoría: $categoryName",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 1.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    AutoSizeText(
                        text = "Jugadores",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp,
                        color = Color.White
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
                            tint = Color.Red.copy(alpha = 0.7f)
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
                        containerColor = Color.Black // Tarjeta negra
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
                            modifier = Modifier.size(48.dp),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                        ) {
                            AutoSizeText(
                                text = "−",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Light
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AutoSizeText(
                                text = "$playerCount",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            AutoSizeText(
                                text = "jugadores",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White.copy(alpha = 0.5f)
                            )
                        }

                        IconButton(
                            onClick = { if (playerCount < 20) playerCount++ },
                            enabled = playerCount < 20,
                            modifier = Modifier.size(48.dp),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                        ) {
                            AutoSizeText(
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
                                containerColor = Color.Black // Tarjeta negra
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
                                        onCheckedChange = { randomStart = it },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color.White,
                                            uncheckedColor = Color.White.copy(alpha = 0.6f),
                                            checkmarkColor = Color.Black
                                        )
                                    )
                                    AutoSizeText(
                                        text = "Inicio aleatorio",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 12.dp),
                                        color = Color.White
                                    )
                                }
                                IconButton(onClick = { showInfoDialog = true }) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Información",
                                        tint = Color.White.copy(alpha = 0.7f)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black // Tarjeta negra
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
                                        onCheckedChange = { votingEnabled = it },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color.White,
                                            uncheckedColor = Color.White.copy(alpha = 0.6f),
                                            checkmarkColor = Color.Black
                                        )
                                    )
                                    AutoSizeText(
                                        text = "Habilitar votaciones",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 12.dp),
                                        color = Color.White
                                    )
                                }
                                IconButton(onClick = { showVotingInfoDialog = true }) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Información",
                                        tint = Color.White.copy(alpha = 0.7f)
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
                                    fontWeight = FontWeight.Light,
                                    color = Color.White.copy(alpha = 0.7f)
                                ) 
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Words
                            ),
                            shape = MaterialTheme.shapes.medium,
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
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                        disabledContentColor = Color.White.copy(alpha = 0.5f)
                    )
                ) {
                    AutoSizeText(
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

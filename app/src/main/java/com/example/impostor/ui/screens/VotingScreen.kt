package com.example.impostor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.data.GameState

@Composable
fun VotingScreen(
    gameState: GameState,
    onVoteConfirmed: (Int) -> Unit
) {
    var selectedPlayerIndex by remember { mutableStateOf<Int?>(null) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121)) // Fondo gris oscuro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                
                Text(
                    text = "¿Quién es el impostor?",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    color = Color.White // Texto blanco
                )
            }
            
            // Grid de jugadores vivos
            val alivePlayers = gameState.players.mapIndexedNotNull { index, player ->
                if (!player.isEliminated) index to player else null
            }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(alivePlayers) { _, (originalIndex, player) ->
                    PlayerVoteCard(
                        playerName = player.name,
                        isSelected = selectedPlayerIndex == originalIndex,
                        onClick = { selectedPlayerIndex = originalIndex }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Botón de comprobar
            Button(
                onClick = {
                    selectedPlayerIndex?.let { onVoteConfirmed(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                enabled = selectedPlayerIndex != null,
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Botón negro
                    contentColor = Color.White,
                    disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                )
            ) {
                Text(
                    text = "Comprobar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun PlayerVoteCard(
    playerName: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                Color(0xFF4CAF50) // Verde si está seleccionado
            else
                Color.Black // Negro si no está seleccionado
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 0.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isSelected) {
                    Text(
                        text = "✓",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Check blanco sobre verde
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                Text(
                    text = playerName,
                    fontSize = if (isSelected) 20.sp else 18.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White, // Texto siempre blanco
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}

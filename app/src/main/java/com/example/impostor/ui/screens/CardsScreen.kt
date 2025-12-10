package com.example.impostor.ui.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.data.GameState

@Composable
fun CardsScreen(
    gameState: GameState,
    onPlayerViewed: (Int) -> Unit,
    onStartDebate: () -> Unit
) {
    var flippedCardIndex by remember { mutableStateOf<Int?>(null) }
    val allCardsViewed = gameState.players.all { it.hasSeenCard }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121)) // Fondo gris oscuro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Toca una tarjeta para revelar tu palabra",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                color = Color.White.copy(alpha = 0.7f) // Texto blanco con transparencia
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(gameState.players) { index, player ->
                    PlayerCard(
                        playerName = player.name,
                        word = if (index == gameState.impostorIndex) "IMPOSTOR" else gameState.selectedWord,
                        hasSeenCard = player.hasSeenCard,
                        isFlipped = flippedCardIndex == index,
                        onCardClick = {
                            if (flippedCardIndex == null) {
                                flippedCardIndex = index
                            }
                        },
                        onReadyClick = {
                            onPlayerViewed(index)
                            flippedCardIndex = null
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onStartDebate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = allCardsViewed,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                )
            ) {
                Text(
                    text = if (allCardsViewed) "Comenzar" else "Revela todas las tarjetas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PlayerCard(
    playerName: String,
    word: String,
    hasSeenCard: Boolean,
    isFlipped: Boolean,
    onCardClick: () -> Unit,
    onReadyClick: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "card flip"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable(enabled = !hasSeenCard && !isFlipped) { onCardClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp,
            hoveredElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black // Tarjeta siempre negra
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                // Cara frontal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = playerName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (hasSeenCard) {
                            Color.White.copy(alpha = 0.5f)
                        } else {
                            Color.White
                        }
                    )
                    if (hasSeenCard) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "✓ Vista",
                            fontSize = 14.sp,
                            color = Color.Green.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                // Cara trasera (volteada)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f }
                        .padding(16.dp)
                ) {
                    Text(
                        text = word,
                        fontSize = if (word == "IMPOSTOR") 24.sp else 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (word == "IMPOSTOR") {
                            Color.Red
                        } else {
                            Color.White
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = onReadyClick,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // Botón blanco para contraste en tarjeta negra
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Listo")
                    }
                }
            }
        }
    }
}

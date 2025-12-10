package com.example.impostor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.R
import com.example.impostor.data.GameState

@Composable
fun VoteResultScreen(
    gameState: GameState,
    votedPlayerIndex: Int,
    isCorrect: Boolean,
    onContinue: () -> Unit
) {
    val votedPlayerName = gameState.players[votedPlayerIndex].name
    val alivePlayers = gameState.players.count { !it.isEliminated }
    val alivePlayersAfterElimination = if (!isCorrect) alivePlayers - 1 else alivePlayers
    val impostorWins = !isCorrect && alivePlayersAfterElimination <= 2
    
    var isNavigating by remember { mutableStateOf(false) }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isNavigating) 
            MaterialTheme.colorScheme.background 
        else if (isCorrect)
            Color(0xFF4CAF50)
        else
            MaterialTheme.colorScheme.errorContainer
    ) {
        if (!isNavigating) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isCorrect) {
                        Image(
                            painter = painterResource(id = R.drawable.impostor_logo_white),
                            contentDescription = "Impostor Logo",
                            modifier = Modifier.size(180.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(40.dp))
                    } else if (impostorWins) {
                        Image(
                            painter = painterResource(id = R.drawable.impostor_logo_black),
                            contentDescription = "Impostor Logo",
                            modifier = Modifier.size(180.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    
                    Text(
                        text = when {
                            isCorrect -> "¡CORRECTO!"
                            impostorWins -> "¡IMPOSTOR GANA!"
                            else -> "¡INCORRECTO!"
                        },
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 48.sp,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    if (!isCorrect && !impostorWins) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black.copy(alpha = 0.6f)
                            ),
                            shape = MaterialTheme.shapes.large,
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "$votedPlayerName ha sido eliminado",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                                
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Text(
                                    text = "Quedan $alivePlayersAfterElimination jugadores vivos",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }
                    } else {
                        Text(
                            text = if (isCorrect)
                                "$votedPlayerName era el impostor"
                            else
                                "El impostor ha ganado",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "La palabra era: ${gameState.selectedWord}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = {
                        isNavigating = true
                        onContinue()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, // Botón negro
                        contentColor = Color.White // Texto blanco
                    )
                ) {
                    Text(
                        text = if (isCorrect || impostorWins) "Nueva Ronda" else "Continuar Ronda",
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

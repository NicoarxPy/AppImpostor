package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.data.GameState

@Composable
fun DebateScreen(
    gameState: GameState,
    onRevealImpostor: () -> Unit,
    onStartVoting: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (gameState.currentStarterIndex != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
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
                                text = "Comienza",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = gameState.players[gameState.currentStarterIndex].name,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
                
                Text(
                    text = "DEBATE",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Discutan y descubran\nal impostor",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = if (gameState.votingEnabled) onStartVoting else onRevealImpostor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (gameState.votingEnabled)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = if (gameState.votingEnabled) "Iniciar Votación" else "Revelar Impostor",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun RevealScreen(
    gameState: GameState,
    onNextRound: () -> Unit
) {
    val impostorName = gameState.players[gameState.impostorIndex].name
    var isNavigating by remember { mutableStateOf(false) }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isNavigating) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.errorContainer
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
                    Text(
                        text = "EL IMPOSTOR ERA",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 3.sp,
                        textAlign = TextAlign.Center,
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = impostorName,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "La palabra era",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Center,
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.6f)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = gameState.selectedWord,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = {
                        isNavigating = true
                        onNextRound()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Siguiente Ronda",
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

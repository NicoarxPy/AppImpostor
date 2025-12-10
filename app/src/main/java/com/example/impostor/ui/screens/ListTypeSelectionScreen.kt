package com.example.impostor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.R

@Composable
fun ListTypeSelectionScreen(
    onCustomListsClick: () -> Unit,
    onPredefinedListsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121)) // Fondo gris oscuro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.impostor_logo),
                contentDescription = "Impostor Logo",
                modifier = Modifier.size(180.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "IMPOSTOR",
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                color = Color.White, // Texto blanco
                letterSpacing = 2.sp
            )
            
            Spacer(modifier = Modifier.height(64.dp))
            
            // Botón de Listas Predeterminadas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black // Tarjeta negra
                ),
                shape = MaterialTheme.shapes.extraLarge,
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                onClick = onPredefinedListsClick
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Listas predeterminadas",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // Texto blanco
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Categorías del juego",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.7f) // Texto blanco con transparencia
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Botón de Listas Personalizadas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black // Tarjeta negra
                ),
                shape = MaterialTheme.shapes.extraLarge,
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                onClick = onCustomListsClick
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Listas personalizadas",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // Texto blanco
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Crea tus propias categorías",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.7f) // Texto blanco con transparencia
                        )
                    }
                }
            }
        }
    }
}

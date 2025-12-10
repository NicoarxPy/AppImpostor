package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onPlayClick: () -> Unit
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
            Spacer(modifier = Modifier.weight(1f))
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🎭",
                    fontSize = 120.sp
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "IMPOSTOR",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "¿Quién es el impostor?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = onPlayClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Jugar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

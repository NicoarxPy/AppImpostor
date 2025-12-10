package com.example.impostor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TransitionScreen(
    onTransitionComplete: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(800) // 0.8 segundos
        onTransitionComplete()
    }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
    }
}

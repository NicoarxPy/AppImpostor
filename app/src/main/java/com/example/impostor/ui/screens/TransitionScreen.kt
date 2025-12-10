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
import kotlinx.coroutines.delay

@Composable
fun TransitionScreen(
    onTransitionComplete: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(800) // 0.8 segundos
        onTransitionComplete()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121)) // Fondo gris oscuro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.impostor_logo),
                contentDescription = "Impostor Logo",
                modifier = Modifier.size(120.dp)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "IMPOSTOR",
                fontSize = 56.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp,
                color = Color.White // Texto blanco
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "¿Quién es el impostor?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp,
                color = Color.White.copy(alpha = 0.6f) // Texto blanco con transparencia
            )
        }
    }
}

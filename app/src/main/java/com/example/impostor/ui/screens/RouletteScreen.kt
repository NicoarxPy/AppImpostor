package com.example.impostor.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.data.GameState
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RouletteScreen(
    gameState: GameState,
    onStarterSelected: (Int) -> Unit
) {
    var isSpinning by remember { mutableStateOf(false) }
    var hasSpun by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    
    val playerNames = gameState.players.map { it.name }
    val colors = remember {
        listOf(
            Color(0xFF90CAF9), // Azul claro
            Color(0xFF64B5F6), // Azul medio claro
            Color(0xFF42A5F5), // Azul medio
            Color(0xFF2196F3), // Azul
            Color(0xFF1E88E5), // Azul medio oscuro
            Color(0xFF1976D2), // Azul oscuro
            Color(0xFFBBDEFB), // Azul muy claro
            Color(0xFF82B1FF), // Azul pastel
            Color(0xFF448AFF)  // Azul brillante suave
        )
    }

    // Animación de rotación
    val targetRotation = remember { mutableStateOf(0f) }
    val rotation = animateFloatAsState(
        targetValue = targetRotation.value,
        animationSpec = tween(
            durationMillis = 4000,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            if (isSpinning) {
                isSpinning = false
                hasSpun = true
                
                // Calcular el índice seleccionado
                val normalizedRotation = (360 - (targetRotation.value % 360)) % 360
                val anglePerSection = 360f / playerNames.size
                val selected = (normalizedRotation / anglePerSection).toInt() % playerNames.size
                selectedIndex = selected
            }
        },
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121)) // Fondo gris oscuro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedIndex == null) {
                Text(
                    text = "¿Quién comienza?",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, // Texto blanco
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = if (!hasSpun) "Toca la ruleta para girarla" else "Girando...",
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.7f), // Texto blanco con transparencia
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            } else {
                Text(
                    text = "¡Comienza!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, // Texto blanco
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black // Tarjeta negra
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = playerNames[selectedIndex!!],
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        color = Color.White // Texto blanco
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(320.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Ruleta
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            enabled = !isSpinning && !hasSpun
                        ) {
                            if (!isSpinning && !hasSpun) {
                                isSpinning = true
                                // Girar entre 5 y 7 vueltas completas más un ángulo aleatorio
                                val extraRotations = (5..7).random() * 360f
                                val randomAngle = (0..359).random().toFloat()
                                targetRotation.value += extraRotations + randomAngle
                            }
                        }
                ) {
                    val anglePerSection = 360f / playerNames.size
                    val radius = size.minDimension / 2
                    val centerX = size.width / 2
                    val centerY = size.height / 2

                    rotate(rotation.value, pivot = Offset(centerX, centerY)) {
                        playerNames.forEachIndexed { index, name ->
                            val startAngle = index * anglePerSection
                            
                            // Dibujar sección
                            drawArc(
                                color = colors[index % colors.size],
                                startAngle = startAngle - 90,
                                sweepAngle = anglePerSection,
                                useCenter = true,
                                topLeft = Offset.Zero,
                                size = Size(size.width, size.height)
                            )
                            
                            // Dibujar borde oscuro entre secciones
                            drawArc(
                                color = Color(0xFF1A1A1A),
                                startAngle = startAngle - 90,
                                sweepAngle = anglePerSection,
                                useCenter = true,
                                topLeft = Offset.Zero,
                                size = Size(size.width, size.height),
                                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3f)
                            )
                            
                            // Dibujar texto
                            val textAngle = (startAngle + anglePerSection / 2 - 90) * (PI / 180).toFloat()
                            val textRadius = radius * 0.65f
                            val textX = centerX + textRadius * cos(textAngle)
                            val textY = centerY + textRadius * sin(textAngle)
                            
                            drawContext.canvas.nativeCanvas.apply {
                                save()
                                translate(textX, textY)
                                rotate((startAngle + anglePerSection / 2).toFloat())
                                
                                val paint = android.graphics.Paint().apply {
                                    color = android.graphics.Color.WHITE
                                    textSize = 52f
                                    textAlign = android.graphics.Paint.Align.CENTER
                                    typeface = android.graphics.Typeface.create(
                                        "sans-serif-light",
                                        android.graphics.Typeface.BOLD
                                    )
                                    isAntiAlias = true
                                    letterSpacing = 0.05f
                                    setShadowLayer(8f, 0f, 2f, android.graphics.Color.argb(180, 0, 0, 0))
                                }
                                
                                drawText(name, 0f, 0f, paint)
                                restore()
                            }
                        }
                    }
                }

                // Indicador superior (flecha)
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val centerX = size.width / 2
                    val arrowSize = 40f
                    
                    // Triángulo apuntando hacia abajo
                    val path = androidx.compose.ui.graphics.Path().apply {
                        moveTo(centerX, arrowSize)
                        lineTo(centerX - arrowSize / 2, 0f)
                        lineTo(centerX + arrowSize / 2, 0f)
                        close()
                    }
                    
                    drawPath(
                        path = path,
                        color = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (selectedIndex != null) {
                Button(
                    onClick = {
                        selectedIndex?.let { onStarterSelected(it) }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Continuar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

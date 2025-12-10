package com.example.impostor.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
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
            Color(0xFFFF1744), // Rojo brillante
            Color(0xFF2979FF), // Azul brillante
            Color(0xFF00E676), // Verde brillante
            Color(0xFFFFEA00), // Amarillo brillante
            Color(0xFFD500F9), // Morado brillante
            Color(0xFFFF6E40), // Naranja brillante
            Color(0xFF00E5FF), // Cyan brillante
            Color(0xFFFF4081), // Rosa brillante
            Color(0xFF76FF03)  // Lima brillante
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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = if (!hasSpun) "Toca la ruleta para girarla" else "Girando...",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            } else {
                Text(
                    text = "¡Comienza!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
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
                        color = MaterialTheme.colorScheme.onPrimaryContainer
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
                                    textSize = 50f
                                    textAlign = android.graphics.Paint.Align.CENTER
                                    isFakeBoldText = true
                                    setShadowLayer(6f, 0f, 0f, android.graphics.Color.BLACK)
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
                        .height(56.dp)
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

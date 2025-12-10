package com.example.impostor.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.impostor.data.PlayerPreferences
import com.example.impostor.ui.screens.*
import com.example.impostor.viewmodel.GameViewModel
import com.example.impostor.viewmodel.GameViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ImpostorNavGraph(
    navController: NavHostController,
    context: Context = LocalContext.current
) {
    val playerPreferences = PlayerPreferences(context)
    val viewModel: GameViewModel = viewModel(
        factory = GameViewModelFactory(playerPreferences)
    )
    val savedPlayers by viewModel.savedPlayerNames.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onPlayClick = {
                    navController.navigate(Screen.CategorySelection.route)
                }
            )
        }

        composable(Screen.CategorySelection.route) {
            CategorySelectionScreen(
                onCategorySelected = { category ->
                    viewModel.selectCategory(category)
                    navController.navigate(Screen.AddPlayers.route)
                }
            )
        }

        composable(Screen.AddPlayers.route) {
            AddPlayersScreen(
                categoryName = viewModel.selectedCategory?.name ?: "",
                savedPlayers = savedPlayers,
                onBackClick = {
                    navController.popBackStack()
                },
                onStartGame = { playerNames, randomStart ->
                    viewModel.addPlayers(playerNames)
                    viewModel.startGame(randomStart)
                    navController.navigate(Screen.Cards.route)
                },
                onClearSavedPlayers = {
                    viewModel.clearSavedPlayers()
                }
            )
        }

        composable(Screen.Roulette.route) {
            viewModel.gameState?.let { gameState ->
                RouletteScreen(
                    gameState = gameState,
                    onStarterSelected = { starterIndex ->
                        viewModel.setStarter(starterIndex)
                        navController.navigate(Screen.Debate.route) {
                            popUpTo(Screen.Roulette.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(Screen.Cards.route) {
            viewModel.gameState?.let { gameState ->
                CardsScreen(
                    gameState = gameState,
                    onPlayerViewed = { playerIndex ->
                        viewModel.markPlayerAsViewed(playerIndex)
                    },
                    onStartDebate = {
                        if (gameState.randomStart) {
                            navController.navigate(Screen.Roulette.route)
                        } else {
                            navController.navigate(Screen.Debate.route)
                        }
                    }
                )
            }
        }

        composable(Screen.Debate.route) {
            viewModel.gameState?.let { gameState ->
                DebateScreen(
                    gameState = gameState,
                    onRevealImpostor = {
                        navController.navigate(Screen.Reveal.route)
                    }
                )
            }
        }

        composable(Screen.Reveal.route) {
            viewModel.gameState?.let { gameState ->
                RevealScreen(
                    gameState = gameState,
                    onNextRound = {
                        viewModel.resetGame()
                        navController.navigate(Screen.Cards.route) {
                            popUpTo(Screen.Cards.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

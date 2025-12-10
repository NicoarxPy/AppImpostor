package com.example.impostor.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object CategorySelection : Screen("category_selection")
    object AddPlayers : Screen("add_players")
    object Transition : Screen("transition")
    object Roulette : Screen("roulette")
    object Cards : Screen("cards")
    object Debate : Screen("debate")
    object Reveal : Screen("reveal")
    object Voting : Screen("voting")
    object VoteResult : Screen("vote_result/{votedPlayerIndex}/{isCorrect}") {
        fun createRoute(votedPlayerIndex: Int, isCorrect: Boolean) = 
            "vote_result/$votedPlayerIndex/$isCorrect"
    }
}

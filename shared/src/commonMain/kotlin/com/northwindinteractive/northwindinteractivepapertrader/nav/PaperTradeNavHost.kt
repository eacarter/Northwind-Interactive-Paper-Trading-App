package com.northwindinteractive.northwindinteractivepapertrader.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.northwindinteractive.northwindinteractivepapertrader.screens.LoginScreen
import com.northwindinteractive.northwindinteractivepapertrader.screens.PortfolioScreen
import com.northwindinteractive.northwindinteractivepapertrader.screens.composables


@Composable
fun PaperTraderNavHost(navController: NavHostController) {

    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Portfolio.route) {
            PortfolioScreen()
        }
//        composable(
//            Screen.MovieDetail.route,
//            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
//        ) {
//            MovieDetailScreen(onBackClick = { navController.popBackStack() })
//        }
//        composable(Screen.Favorites.route) {
//            FavoritesScreen(onMovieClick = { movieId ->
//                navController.navigate(Screen.MovieDetail.createRoute(movieId))
//            })
//        }
//        composable(Screen.Profile.route) {
//            ProfileScreen(navController = navController)
//        }
//        composable(Screen.Search.route) {
//            SearchScreen(onMovieClick = { movieId ->
//                navController.navigate(Screen.MovieDetail.createRoute(movieId))
//            })
//        }
        composable(Screen.Login.route){
            LoginScreen(
                navController = navController
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login_view")
    object Portfolio : Screen("portfolio_view")
    object Research :Screen("research_view")
    object ResearchDetail : Screen("research_detail{id}"){
        fun createRoute(id: Int) = "research_detail/$id"
    }
    object Trade : Screen("trade_view")
    object Automation : Screen("automation_view")
    object More : Screen("move_view")
    object TradeExplanation : Screen("trade_explanation_view")
    object Connections : Screen("connection_view")
    object RiskControl : Screen("risk_control_view")
    object Settings : Screen("settings_view")
}
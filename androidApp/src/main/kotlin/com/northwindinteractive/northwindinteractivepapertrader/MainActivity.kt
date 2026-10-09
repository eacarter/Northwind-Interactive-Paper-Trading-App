package com.northwindinteractive.northwindinteractivepapertrader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.northwindinteractive.northwindinteractivepapertrader.di.initKoin
import com.northwindinteractive.northwindinteractivepapertrader.nav.PaperTraderNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            PaperTradeApp()
        }
    }
}

@PreviewScreenSizes
@Composable
fun PaperTradeApp(navController: NavHostController = rememberNavController()) {
    val backEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backEntry?.destination?.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            if (currentDestination in AppDestinations.entries.map { it.route }){
                AppDestinations.entries.forEach { destinations ->
                    item(
                        icon = {
                            Icon(
                                destinations.icon,
                                contentDescription = destinations.label
                            )
                        },
                        label = { Text(destinations.label) },
                        selected = currentDestination == destinations.route,
                        onClick = {
                            navController.navigate(destinations.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
       PaperTraderNavHost(navController = navController)
    }
}

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
//    HOME("movie_list", "Home", Icons.Default.Home),
//    FAVORITES("favorite_list", "Favorites", Icons.Default.Favorite),
//    PROFILE("profile_view", "Profile", Icons.Default.AccountBox),
//    SEARCH("search_view", "Search", Icons.Default.Search)
}
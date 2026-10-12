package com.northwindinteractive.northwindinteractivepapertrader

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.northwindinteractive.northwindinteractivepapertrader.nav.PaperTraderNavHost
import northwindinteractivepapertrader.shared.generated.resources.Res
import northwindinteractivepapertrader.shared.generated.resources.ic_automations
import northwindinteractivepapertrader.shared.generated.resources.ic_more
import northwindinteractivepapertrader.shared.generated.resources.ic_portfolio
import northwindinteractivepapertrader.shared.generated.resources.ic_research
import northwindinteractivepapertrader.shared.generated.resources.ic_trade
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.collections.contains

@Composable
fun App(){
    PaperTradeApp()
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
                                painterResource(destinations.icon),
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
    val icon: DrawableResource,
) {
    PORTFOLIO("portfolio_view", "Portfolio", Res.drawable.ic_portfolio),
    RESEARCH("research_view", "Research", Res.drawable.ic_research),
    TRADE("trade_view", "Trade", Res.drawable.ic_trade),
    AUTOMATION("automation_view", "Automation", Res.drawable.ic_automations),
    MORE("more_view", "More", Res.drawable.ic_more)
}
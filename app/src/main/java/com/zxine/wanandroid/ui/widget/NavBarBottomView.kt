package com.zxine.wanandroid.ui.widget

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zxine.wanandroid.R
import com.zxine.wanandroid.ui.router.AppRouter

sealed class BaseBottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Home: BaseBottomNavRoute(AppRouter.HOME, R.string.home, Icons.Default.Home)

    object Collection: BaseBottomNavRoute(AppRouter.COLLECTION, R.string.collection, Icons.Default.Favorite)

    object Profile: BaseBottomNavRoute(AppRouter.PERSION, R.string.profile, Icons.Default.Person)
}

@Composable
fun MainBottomNavBarView(navController: NavHostController) {

    val bottomNavList = listOf(
        BaseBottomNavRoute.Home,
        BaseBottomNavRoute.Collection,
        BaseBottomNavRoute.Profile,
    )

    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
        ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavList.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true

            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.White,
                onClick = {
                          println("routerName: ${screen.routeName}")
                    if (currentDestination?.route != screen.routeName) {
                        navController.navigate(screen.routeName) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                },
                icon = {
                       Icon(
                           imageVector = screen.icon,
                           contentDescription = null,
                           tint = if (selected) Color.White else Color.LightGray,
                           modifier = if (selected) Modifier.padding(2.dp) else Modifier.padding(1.dp)
                           )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.stringId),
                        color = if (selected) Color.White else Color.LightGray,
                        modifier = Modifier.padding(3.dp),
                        textAlign = TextAlign.Justify,
                        fontStyle = FontStyle.Italic
                        )
                })
        }
    }
}
package com.zxine.wanandroid.ui.page.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zxine.wanandroid.ui.page.collect.CollectPage
import com.zxine.wanandroid.ui.page.home.HomePage
import com.zxine.wanandroid.ui.page.person.PersonPage
import com.zxine.wanandroid.ui.router.AppRouter
import com.zxine.wanandroid.ui.widget.MainBottomNavBarView

@Composable
fun MainPage(navHostController: NavHostController, scaffoldState: ScaffoldState) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { bottomBarWidget(navHostController) },
        content = {
            NavHost(
                navController = navHostController,
                startDestination = AppRouter.HOME,
            ) {
                // 首页
                composable(route = AppRouter.HOME) {
                    HomePage()
                }

                // 搜藏
                composable(route = AppRouter.COLLECTION) {
                    CollectPage()
                }

                // 我的
                composable(route = AppRouter.PERSION) {
                    PersonPage()
                }
            }
        }
    )
}

@Composable
fun bottomBarWidget(navController: NavHostController) {
    val bottomBaseColor = Color.Transparent
    val navbackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navbackStackEntry?.destination
    Box(
        modifier = Modifier.background(
            brush = Brush.radialGradient(
                listOf(
                    bottomBaseColor.copy(alpha = 0.65f),
                    bottomBaseColor.copy(alpha = 0.35f),
                ),
                radius = 0.01f
            )
        )
    ) {
        when (currentDestination?.route) {
            AppRouter.HOME -> MainBottomNavBarView(navController = navController)
            AppRouter.COLLECTION -> MainBottomNavBarView(navController = navController)
            AppRouter.PERSION -> MainBottomNavBarView(navController = navController)
        }
    }
}
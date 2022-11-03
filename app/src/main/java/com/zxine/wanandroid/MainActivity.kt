package com.zxine.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.zxine.wanandroid.ui.page.SplashPage
import com.zxine.wanandroid.ui.page.main.MainPage
import com.zxine.wanandroid.ui.router.PageNavRouter
import com.zxine.wanandroid.ui.router.PageType
import com.zxine.wanandroid.ui.theme.BaseBrush
import com.zxine.wanandroid.ui.theme.WanAndroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { navStartPage() }
    }
}


@Composable
fun navStartPage() {

    val pageType = PageNavRouter.instance.currentPageType.observeAsState()

    WanAndroidTheme {
        // 创建一个导航控制器
        val navHostController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        Crossfade(modifier = Modifier
            .fillMaxSize()
            .background(brush = BaseBrush()),
            targetState = pageType,
            animationSpec = SnapSpec(50)
            ) {
            when (it.value) {
                PageType.SplashPageType -> {
                    SplashPage()
                }
                PageType.MainPageType -> {
                    MainPage(navHostController = navHostController, scaffoldState = scaffoldState)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WanAndroidTheme {
        SplashPage()
    }
}
package com.zxine.wanandroid.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.zxine.wanandroid.ui.router.PageNavRouter
import com.zxine.wanandroid.ui.router.PageType
import kotlinx.coroutines.delay


@Composable
fun SplashPage() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ){
        LaunchedEffect(Unit) {
            delay(500)
            PageNavRouter.instance.updatePageType(PageType.MainPageType)
        }
        Text(
            text = "玩 Andorid",
            color = Color.White,
            fontSize = 48.sp,
            textAlign = TextAlign.Justify,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.W900,
        )
    }
    
}
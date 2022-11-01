package com.zxine.wanandroid.ui.page.home

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zxine.wanandroid.ui.page.home.question.QuestionPage
import com.zxine.wanandroid.ui.page.home.recommend.RecommendPage
import com.zxine.wanandroid.ui.page.home.square.SquarePage
import com.zxine.wanandroid.viewmodel.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(navCtrl: NavHostController,
             scaffoldState: ScaffoldState,
             viewModel: HomeViewModel = hiltViewModel()) {

    val titles = viewModel.viewStates.titles
    val uiController = rememberSystemUiController()
    val pageState = rememberPagerState(initialPage = viewModel.pageIndex)

    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {
        val currentModifier = if (uiController.isStatusBarVisible) {
            Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(0.35f))
                .statusBarsPadding()
        } else {
            Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(0.35f))
        }

        // Content
        Box(modifier = currentModifier) {
            Column() {
                TabRow(
                    selectedTabIndex = pageState.currentPage,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    backgroundColor = Color.Transparent,
                    indicator = @Composable {
                        val currentTabPosition = it[pageState.currentPage]
                        val currentTabWidth by animateDpAsState(
                            targetValue = currentTabPosition.width / 5,
                            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                        )
                        val indicatorOffset:Dp by animateDpAsState(
                            targetValue = currentTabPosition.left + (currentTabPosition.width / 2 - currentTabWidth / 2),
                            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                        )
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.BottomStart)
                                .offset(x = indicatorOffset)
                                .width(currentTabWidth)
                                .padding(bottom = 10.dp)
                                .height(1.dp),
                            color = Color.White
                        )
                    }
                ) {
                    titles.forEachIndexed { index, tabTitle ->

                        Tab(
                            selected = pageState.currentPage == index,
                            onClick = {
                                if (pageState.currentPage != index){
                                    CoroutineScope(Dispatchers.Main).launch {
                                        Log.i("KT","changeIndex$index,lastVue${pageState.currentPage}")
                                        pageState.scrollToPage(index)
                                    }
                                }
                            },
                            text = {
                                Text(
                                    text = tabTitle.text,
                                    style = if (pageState.currentPage == index) {
                                        MaterialTheme.typography.body1
                                    } else {
                                        MaterialTheme.typography.body2
                                    },
                                    color = Color.White
                                )
                            }
                        )

                    }
                }
            }
        }

        // tabbar
        HorizontalPager(
            count = titles.size,
            state = pageState,
            ) {page ->
            when (page) {
                0 -> SquarePage()
                1 -> RecommendPage()
                2 -> QuestionPage()
            }

        }
    }
    
}
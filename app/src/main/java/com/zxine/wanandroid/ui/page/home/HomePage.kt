package com.zxine.wanandroid.ui.page.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zxine.wanandroid.ui.page.home.question.QuestionPage
import com.zxine.wanandroid.ui.page.home.recommend.RecommendPage
import com.zxine.wanandroid.ui.page.home.square.SquarePage
import com.zxine.wanandroid.ui.viewmodel.home.HomeViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(navCtrl: NavHostController,
             scaffoldState: ScaffoldState,
             viewModel: HomeViewModel = hiltViewModel()) {

    val titles = viewModel.viewStates.titles

    val pageState = rememberPagerState(initialPage = viewModel.pageIndex)

    Column(modifier = Modifier.fillMaxSize()) {

        // Content
        Box(modifier = Modifier.fillMaxSize(),
            ) {
            Text(text = "我是内容")
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
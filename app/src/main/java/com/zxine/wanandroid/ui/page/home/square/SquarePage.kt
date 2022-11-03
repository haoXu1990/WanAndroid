package com.zxine.wanandroid.ui.page.home.square

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.zxine.wanandroid.ui.widget.RefreshWidget
import com.zxine.wanandroid.view.module.home.SquareViewModule

@Composable
fun SquarePage(
    navHostCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: SquareViewModule = hiltViewModel()
) {
   val viewState = remember {
       viewModel.viewStates
   }
    val squareData = viewState.pagingData.collectAsLazyPagingItems()
    val listState = if (squareData.itemCount > 0) viewState.listState else LazyListState()

    RefreshWidget(lazyPagingItems = squareData) {
        itemsIndexed(squareData) {_, item ->
            Box(modifier = Modifier.padding(20.dp)) {
                Text(text = item?.author ?: "没有作者")
            }
        }
    }
}
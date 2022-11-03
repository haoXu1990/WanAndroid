package com.zxine.wanandroid.view.module.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zxine.wanandroid.common.paging.easyPager
import com.zxine.wanandroid.net.api.WanAndroidNetService
import com.zxine.wanandroid.net.bean.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SquareViewModule @Inject constructor(
    private var service: WanAndroidNetService
) : ViewModel() {
    private val pager by lazy {
        easyPager {
            delay(2000)
            service.getSquareData(it)
        }.cachedIn(viewModelScope)
    }

    var viewStates by mutableStateOf(SquareViewState(pagingData = pager))
        private set
}

data class SquareViewState(
    val isRefreshing: Boolean = false,
    val listState: LazyListState = LazyListState(),
    val pagingData: Flow<PagingData<Article>>
)

sealed class SquareViewAction {
    object Refresh: SquareViewAction()
}
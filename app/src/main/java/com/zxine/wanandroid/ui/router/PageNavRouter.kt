package com.zxine.wanandroid.ui.router

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PageNavRouter private constructor() {
    private val _currentPageType = MutableLiveData<PageType>(PageType.SplashPageType)

    val currentPageType: LiveData<PageType> = _currentPageType

    fun updatePageType(type: PageType) {
        _currentPageType.postValue(type)
    }

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { PageNavRouter() }
    }
}

sealed class PageType {

    object MainPageType: PageType()

    object SplashPageType: PageType()
}
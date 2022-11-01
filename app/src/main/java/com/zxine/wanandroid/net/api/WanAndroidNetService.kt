package com.zxine.wanandroid.net.api

import com.zxine.wanandroid.net.bean.Article
import com.zxine.wanandroid.net.bean.BasicBean
import com.zxine.wanandroid.net.bean.ListWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidNetService {
    companion object {
        const val url = "https://www.wanandroid.com"
    }

    // 首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): BasicBean<ListWrapper<Article>>
}
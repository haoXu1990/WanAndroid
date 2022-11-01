package com.zxine.wanandroid.common.net


sealed class HttpResult <out T> {

    data class Success<T>(val result: T): HttpResult<T>()

    data class Error(val exception: Exception): HttpResult<Nothing>()
}
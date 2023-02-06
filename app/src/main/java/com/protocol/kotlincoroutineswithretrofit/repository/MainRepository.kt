package com.protocol.kotlincoroutineswithretrofit.repository

import com.protocol.kotlincoroutineswithretrofit.api.RetrofitService

class MainRepository constructor(
    private val retrofitService: RetrofitService
) {
    suspend fun getAllMovies() = retrofitService.getAllMovies()
}
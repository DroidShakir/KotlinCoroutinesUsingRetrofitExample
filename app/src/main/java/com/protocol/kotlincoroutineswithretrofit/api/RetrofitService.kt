package com.protocol.kotlincoroutineswithretrofit.api

import com.protocol.kotlincoroutineswithretrofit.model.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>

    companion object {
        private var retrofitService: RetrofitService? = null

        //Creating the Retrofit service instance using the retrofit
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
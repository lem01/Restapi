package com.example.restapi.data

import com.example.restapi.data.model.MovieResult
import com.example.restapi.data.model.TopRatedResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieResult

    @GET("tv/top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TopRatedResult


    object ServiceFactory {
        fun makeService(): Service {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service::class.java)
        }
    }

}
package com.example.restapi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    )

    object ServiceFactory{
        fun makeService():Service{
            return Retrofit.Builder().baseUrl("https://themoviedb.org/3")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service::class.java)
        }
    }
}
package com.example.restapi.data

import com.example.restapi.data.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    @GET("api/users")
    suspend fun gitListUsers(
        @Query("page") page:Int = 2,
    ): ListUsers

    @GET("tv/top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TopRatedResult



    @POST("api/register")
    suspend fun registerUser(
        @Body user: User // Aquí asumo que tienes un modelo de datos llamado User para representar la información del usuario
    ): UserResponse

    @PUT("api/users/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: Int,  // El ID del usuario que deseas actualizar
        @Body updatedUser: Map<String, String>  // Los nuevos datos del usuario que deseas enviar en el cuerpo de la solicitud
    ): Respuesta

    object ServiceFactory {
//        fun makeService(): RetrofitService {
//            return Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/3/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(RetrofitService::class.java)
//        }

        fun makeService(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }

}
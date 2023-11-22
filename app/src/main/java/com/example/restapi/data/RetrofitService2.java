//package com.example.restapi.data;
//
//import com.example.restapi.data.model.ListUsers;
//t
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//
//interface RetrofitService2 {
//
//    @GET("discover/movie?sort_by=popularity.desc")
//    fun listUsers(
//            @Query("api_key") apiKey: String,
//            @Query("region") region: String
//    ): ListUsers
//
//    @GET("tv/top_rated")
//    suspend fun getTopRated(
//            @Query("api_key") api_key: String,
//            @Query("language") language: String = "en-US",
//            @Query("page") page: Int = 1
//    ): ListUsers
//
//
//    object ServiceFactory2 {
//        fun makeService(): RetrofitService2 {
//            return Retrofit.Builder()
//                    .baseUrl("https://api.themoviedb.org/3/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build().create(RetrofitService::class.java)
//        }
//    }
//
//}
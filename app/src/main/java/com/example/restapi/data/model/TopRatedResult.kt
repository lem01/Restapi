package com.example.restapi.data.model

data class TopRatedResult(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)
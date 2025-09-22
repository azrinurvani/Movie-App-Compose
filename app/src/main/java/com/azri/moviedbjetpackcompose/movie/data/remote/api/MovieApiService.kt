package com.azri.moviedbjetpackcompose.movie.data.remote.api

import com.azri.moviedbjetpackcompose.BuildConfig
import com.azri.moviedbjetpackcompose.movie.data.remote.models.MovieDto
import com.azri.moviedbjetpackcompose.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult : Boolean = false
    ) : MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovie(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult : Boolean = false
    ) : MovieDto
}
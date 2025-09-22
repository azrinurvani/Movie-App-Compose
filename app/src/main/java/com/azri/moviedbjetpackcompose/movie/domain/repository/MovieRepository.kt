package com.azri.moviedbjetpackcompose.movie.domain.repository

import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.utils.Response
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun fetchDiscoverMovie() : Flow<Response<List<Movie>>>
    fun fetchTrendingMovie() : Flow<Response<List<Movie>>>
}
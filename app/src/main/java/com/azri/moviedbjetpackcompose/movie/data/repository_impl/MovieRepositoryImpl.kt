package com.azri.moviedbjetpackcompose.movie.data.repository_impl

import com.azri.moviedbjetpackcompose.common.data.ApiMapper
import com.azri.moviedbjetpackcompose.movie.data.remote.api.MovieApiService
import com.azri.moviedbjetpackcompose.movie.data.remote.models.MovieDto
import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.movie.domain.repository.MovieRepository
import com.azri.moviedbjetpackcompose.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
) : MovieRepository {

    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchDiscoverMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchTrendingMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }
}
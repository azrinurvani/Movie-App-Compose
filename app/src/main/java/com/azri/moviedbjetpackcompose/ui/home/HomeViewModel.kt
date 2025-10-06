package com.azri.moviedbjetpackcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.movie.domain.repository.MovieRepository
import com.azri.moviedbjetpackcompose.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchDiscoverMovie()
        fetchTrendingMovie()
    }

    private fun fetchDiscoverMovie() = viewModelScope.launch(Dispatchers.IO) {
        repository.fetchDiscoverMovie().collectAndHandle(
            onError = { error->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        error = error?.message
                    )
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
        ) { movies ->
            _homeState.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    discoverMovies = movies
                )
            }
        }
    }

    private fun fetchTrendingMovie() = viewModelScope.launch {
        repository.fetchTrendingMovie().collectAndHandle(
            onError = { error->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        error = error?.message
                    )
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
        ) { movies ->
            _homeState.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    trendingMovies = movies
                )
            }
        }
    }

}

data class HomeState(
    val discoverMovies : List<Movie> = emptyList(),
    val trendingMovies : List<Movie> = emptyList(),
    val error : String? = null,
    val isLoading : Boolean = false
)
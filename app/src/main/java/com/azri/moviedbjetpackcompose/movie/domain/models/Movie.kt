package com.azri.moviedbjetpackcompose.movie.domain.models

data class Movie(
    val backdropPath: String? = null,
    val genreIds: List<String>? = null, //Ada kesalahan pada genreId apakah berupa list atau tidak pada Domain
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)

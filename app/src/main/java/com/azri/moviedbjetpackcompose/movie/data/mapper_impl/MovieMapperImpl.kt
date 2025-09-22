package com.azri.moviedbjetpackcompose.movie.data.mapper_impl

import com.azri.moviedbjetpackcompose.common.data.ApiMapper
import com.azri.moviedbjetpackcompose.movie.data.remote.models.MovieDto
import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.utils.GenreConstants

class MovieMapperImpl : ApiMapper<List<Movie>, MovieDto> {

    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        return apiDto.results?.map { result ->
            Movie(
                backdropPath = formatEmptyValue(result?.backdropPath),
                genreIds = formatGenre(result?.genreIds),
                id = result?.id,
                originalLanguage = formatEmptyValue(result?.originalLanguage),
                originalTitle = formatEmptyValue(result?.originalTitle, default = "original title"),
                overview = formatEmptyValue(result?.overview, default = "overview"),
                popularity = result?.popularity ?: 0.0,
                posterPath = formatEmptyValue(result?.posterPath),
                releaseDate = formatEmptyValue(result?.releaseDate, default = "date"),
                title = formatEmptyValue(result?.title, default = "Title"),
                video = result?.video ?: false,
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0

            )
        } ?: emptyList()
    }

    private fun formatEmptyValue(value : String?, default:String? = "") : String{
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatGenre(genreIds: List<Int?>?) : List<String>{
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }
}
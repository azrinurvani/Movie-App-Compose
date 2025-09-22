package com.azri.moviedbjetpackcompose.di

import com.azri.moviedbjetpackcompose.common.data.ApiMapper
import com.azri.moviedbjetpackcompose.movie.data.mapper_impl.MovieMapperImpl
import com.azri.moviedbjetpackcompose.movie.data.remote.api.MovieApiService
import com.azri.moviedbjetpackcompose.movie.data.remote.models.MovieDto
import com.azri.moviedbjetpackcompose.movie.data.repository_impl.MovieRepositoryImpl
import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.movie.domain.repository.MovieRepository
import com.azri.moviedbjetpackcompose.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        apiMapper: ApiMapper<List<Movie>, MovieDto>
    ) : MovieRepository{
        return MovieRepositoryImpl(movieApiService,apiMapper)
    }

    @Provides
    @Singleton
    fun provideMovieMapper() : ApiMapper<List<Movie>, MovieDto>{
        return MovieMapperImpl()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder() : Retrofit{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit) : MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }
}
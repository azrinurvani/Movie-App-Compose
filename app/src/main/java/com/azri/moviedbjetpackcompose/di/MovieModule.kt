package com.azri.moviedbjetpackcompose.di

import android.util.Log
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor{ message ->
            Log.d("API-LOG", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieMapper() : ApiMapper<List<Movie>, MovieDto>{
        return MovieMapperImpl()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit) : MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }
}
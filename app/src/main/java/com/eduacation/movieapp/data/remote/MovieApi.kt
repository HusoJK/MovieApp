package com.eduacation.movieapp.data.remote

import com.eduacation.movieapp.BuildConfig
import com.eduacation.movieapp.data.remote.dto.MovieDetailDto
import com.eduacation.movieapp.data.remote.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi{

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString : String,
        @Query("apikey") apiKey : String = BuildConfig.API_KEY
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbID : String,
        @Query("apikey") apiKey : String = BuildConfig.API_KEY
    ) : MovieDetailDto

}
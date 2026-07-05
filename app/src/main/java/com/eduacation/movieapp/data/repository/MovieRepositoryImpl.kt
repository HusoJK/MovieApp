package com.eduacation.movieapp.data.repository

import com.eduacation.movieapp.data.remote.MovieApi
import com.eduacation.movieapp.data.remote.dto.MovieDetailDto
import com.eduacation.movieapp.data.remote.dto.MoviesDto
import com.eduacation.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}
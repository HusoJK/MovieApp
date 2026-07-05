package com.eduacation.movieapp.domain.repository

import com.eduacation.movieapp.data.remote.dto.MovieDetailDto
import com.eduacation.movieapp.data.remote.dto.MoviesDto

interface MovieRepository {
    suspend fun getMovies(search : String) : MoviesDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto
}
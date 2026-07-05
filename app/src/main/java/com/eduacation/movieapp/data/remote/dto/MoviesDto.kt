package com.eduacation.movieapp.data.remote.dto

import com.eduacation.movieapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val search: List<Search>,
    @SerializedName("totalResults") val totalResults: String
)

fun MoviesDto.toMovieList(): List<Movie> {
    return search.map { item -> Movie(item.poster, item.title, item.year, item.imdbID) }
}
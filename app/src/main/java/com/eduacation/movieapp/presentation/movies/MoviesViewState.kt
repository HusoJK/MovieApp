package com.eduacation.movieapp.presentation.movies

import com.eduacation.movieapp.domain.model.Movie

data class MoviesViewState(
    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search : String = "batman"
)
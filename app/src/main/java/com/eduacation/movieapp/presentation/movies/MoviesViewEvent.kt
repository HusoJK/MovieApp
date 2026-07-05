package com.eduacation.movieapp.presentation.movies

sealed class MoviesViewEvent{
    data class Search(val search : String) : MoviesViewEvent()
}
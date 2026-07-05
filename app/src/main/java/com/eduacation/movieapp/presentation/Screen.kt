package com.eduacation.movieapp.presentation

sealed class Screen(val route: String) {
    object MovieScreen : Screen("MOVIE_SCREEN")
    object MovieDetailScreen : Screen("MOVIE_DETAIL_SCREEN")
}
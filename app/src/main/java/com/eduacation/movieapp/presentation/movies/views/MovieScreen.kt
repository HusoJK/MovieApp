package com.eduacation.movieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.eduacation.movieapp.presentation.Screen
import com.eduacation.movieapp.presentation.movies.MoviesViewEvent
import com.eduacation.movieapp.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(navController: NavController, viewModel: MoviesViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.Black)
        ) {

            Column {
                Searchbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    hint = "Batman",
                    onSearch = {
                        viewModel.onEvent(MoviesViewEvent.Search(it))
                    })

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.movies) { movies ->

                        MovieListRow(movie = movies, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${movies.imdbID}")
                        })

                    }
                }
            }
        }
    }

}


/*@Preview
@Composable
fun MovieScreenPreview(){
    MovieScreen(navController = rememberNavController())
}*/
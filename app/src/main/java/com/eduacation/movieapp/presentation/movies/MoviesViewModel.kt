package com.eduacation.movieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduacation.movieapp.domain.useCase.getMovies.GetMovieUseCase
import com.eduacation.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf<MoviesViewState>(MoviesViewState())
    val state: State<MoviesViewState> = _state

    private var job: Job? = null

    init {
        getMovies(_state.value.search)
    }
    private fun getMovies(search: String) {
        job?.cancel()
        job = getMovieUseCase.executeGetMovies(search).onEach {

            when (it) {
                is Resource.Success -> {
                    _state.value = MoviesViewState(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MoviesViewState(error = "Error")
                }
                is Resource.Loading -> {
                    _state.value = MoviesViewState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event : MoviesViewEvent){
        when(event){
            is MoviesViewEvent.Search -> {
                getMovies(search = event.search)
            }
        }
    }

}
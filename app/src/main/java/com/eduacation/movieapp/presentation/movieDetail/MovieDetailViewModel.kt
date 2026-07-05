package com.eduacation.movieapp.presentation.movieDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduacation.movieapp.domain.useCase.getMovieDetail.GetMovieDetailUseCase
import com.eduacation.movieapp.util.Constants.IMDB_ID
import com.eduacation.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<MovieDetailsViewState>(MovieDetailsViewState())
    val state : State<MovieDetailsViewState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }


    private fun getMovieDetail(imdbId : String){
        getMovieDetailUseCase.executeGetMovieDetails(imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MovieDetailsViewState(movie = it.data)
                }
                is Resource.Error ->{
                    _state.value = MovieDetailsViewState(error = it.message ?: "error")

                }
                is Resource.Loading ->{
                    _state.value = MovieDetailsViewState(isLoading = true)

                }

            }
        }.launchIn(viewModelScope)
    }

}
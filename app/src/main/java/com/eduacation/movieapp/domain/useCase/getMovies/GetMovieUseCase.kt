package com.eduacation.movieapp.domain.useCase.getMovies

import coil3.network.HttpException
import com.eduacation.movieapp.data.remote.dto.Search
import com.eduacation.movieapp.data.remote.dto.toMovieList
import com.eduacation.movieapp.domain.model.Movie
import com.eduacation.movieapp.domain.repository.MovieRepository
import com.eduacation.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)

            if (movieList.response == "True") {
                emit(Resource.Success(movieList.toMovieList())) // DTO içine yazılan extension fun sayesinde MovieListe dönüştürdük
            } else {
                emit(Resource.Error("No Movie Found!!!"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No Internet Connection!!"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }

}
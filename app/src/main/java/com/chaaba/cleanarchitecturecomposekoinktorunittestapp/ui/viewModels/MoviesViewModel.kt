package com.chaaba.cleanarchitecturecomposekoinktorunittestapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaaba.core.movies.Movies
import com.chaaba.core.movies.getAllMovies
import com.chaaba.core.movies.sortMoviesByName
import com.chaaba.core.movies.sortMoviesByRating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MoviesViewModel(
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val movies: MutableLiveData<Movies> = MutableLiveData(),

    private val sortMoviesByNameUseCase: (Movies?) -> Movies? = { sortMoviesByName(it) },
    private val sortMoviesByRatingUseCase: (Movies?) -> Movies? = { sortMoviesByRating(it) },
    showAllMoviesUseCase: suspend () -> Movies? = { getAllMovies() },

    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        movies.value = listOf()
        progress.value = true
        viewModelScope.launch(dispatcher) {
            movies.postValue(showAllMoviesUseCase() ?: listOf())
            progress.postValue(false)
        }
    }

    fun sortByName() {
        movies.value = sortMoviesByNameUseCase(movies.value)
    }

    fun sortByRating() {
        movies.value = sortMoviesByRatingUseCase(movies.value)
    }

}
package com.chaaba.cleanarchitecturecomposekoinktorunittestapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaaba.core.favorites.markAsFavorite
import com.chaaba.core.favorites.markAsNotFavorite
import com.chaaba.core.favorites.showAllFavorites
import com.chaaba.core.movies.Movie
import com.chaaba.core.movies.Movies

class FavoritesViewModel(
    val favorites: MutableLiveData<Movies> = MutableLiveData(),
    private val showAllFavoritesUseCase: () -> Movies = { showAllFavorites() },
    private val markAsFavoriteUseCase: (Movie) -> Unit = { markAsFavorite(it)},
    private val markAsNotFavoriteUseCase: (Movie) -> Unit = { markAsNotFavorite(it)}
) : ViewModel() {

    init {
        favorites.value = showAllFavoritesUseCase()
    }

    fun markFavorite(movie: Movie){
        markAsFavoriteUseCase(movie)
        favorites.value = showAllFavoritesUseCase()
    }

    fun markNotFavorite(movie: Movie){
        markAsNotFavoriteUseCase(movie)
        favorites.value = showAllFavoritesUseCase()
    }
}
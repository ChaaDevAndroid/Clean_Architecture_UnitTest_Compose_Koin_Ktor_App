package com.chaaba.core.favorites

import com.chaaba.core.movies.Movie
import com.chaaba.core.movies.Movies
import com.chaaba.core.movies.MoviesGateway
import com.chaaba.core.MoviesGateway
import com.chaaba.core.FavoritesGateway

fun showAllFavorites(
    favoritesGateway: FavoritesGateway = FavoritesGateway,
    moviesGateway: MoviesGateway = MoviesGateway
): Movies {
    return favoritesGateway.loadAllFavorites()
        ?.map { it.movieId }
        ?.let { toMoviesWithSameIds(it, moviesGateway) }
        ?: listOf()
}

private fun toMoviesWithSameIds(favoritesIds: List<Long?>, gateway: MoviesGateway) =
    gateway.loadAllMovies()?.filter { favoritesIds.contains(it.id) }

fun markAsFavorite(movie: Movie, gateway: FavoritesGateway = FavoritesGateway) {
    gateway.loadAllFavorites()
        ?.none { it.movieId == movie.id }
        ?.run { gateway.addToFavorites(Favorite(movie.id)) }
}

fun markAsNotFavorite(movie: Movie, gateway: FavoritesGateway = FavoritesGateway) {
    gateway.loadAllFavorites()
        ?.any { it.movieId == movie.id }
        ?.run { gateway.removeFromFavorites(Favorite(movie.id)) }
}


package com.chaaba.core.movies

import java.lang.UnsupportedOperationException
import com.chaaba.core.MoviesGateway


suspend fun getAllMovies(gateway: MoviesGateway = MoviesGateway): Movies {
    return runCatching { gateway.requestAllMovies() }
        .onSuccess { gateway.saveAllMovies(it) }
        .getOrThrow()
}

fun sortMoviesByName(movies: Movies?) = movies?.sortedBy { it.name }

fun sortMoviesByRating(movies: Movies?) =
    movies?.sortedByDescending { it.rating } ?: throw UnsupportedOperationException()
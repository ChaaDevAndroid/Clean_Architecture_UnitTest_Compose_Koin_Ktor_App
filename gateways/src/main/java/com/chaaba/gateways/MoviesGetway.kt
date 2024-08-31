package com.chaaba.gateways

import com.chaaba.core.FailedToRequestException
import com.chaaba.core.movies.Movies
import com.chaaba.core.movies.MoviesGateway

class MoviesGatewayImplementer : MoviesGateway {

    @Throws(FailedToRequestException::class)
    override suspend fun requestAllMovies(): Movies {
        TODO("Not yet implemented")
    }

    override fun saveAllMovies(movies: Movies) {
        TODO("Not yet implemented")
    }

    override fun loadAllMovies(): Movies? {
        TODO("Not yet implemented")
    }
}
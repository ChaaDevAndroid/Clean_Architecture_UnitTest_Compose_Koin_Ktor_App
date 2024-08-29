package com.chaaba.core.movies

import com.chaaba.core.FailedToRequestException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MoviesUseCasesKtTest {

    @Test
    fun `getAllMovies() then invoke requestAllMovies()`() = runBlocking {
        //arrange
        var invoked = false

        val moviesGateway = object : MoviesGateway {
            override suspend fun requestAllMovies(): Movies {
                invoked = true
                return listOf(Movie(), Movie())
            }
        }

        //act
        getAllMovies(moviesGateway)

        //assert
        assert(invoked)
    }

    @Test
    fun `getAllMovies() with successful requestAllMovies() then invoke saveAllMovies()`() =
        runBlocking {
            //arrange
            var invoked = false

            val moviesGateway = object : MoviesGateway {
                override suspend fun requestAllMovies(): Movies {
                    return listOf(Movie(), Movie())
                }

                override fun saveAllMovies(movies: Movies) {
                    invoked = true
                    super.saveAllMovies(movies)
                }
            }

            //act
            getAllMovies(moviesGateway)

            //assert
            assert(invoked)

        }


    @Test
    fun `getAllMovies() with successful requestAllMovies() then return a list of movies `() =
        runBlocking {
            //arrange
            val moviesGateway = object : MoviesGateway {
                override suspend fun requestAllMovies(): Movies {
                    return listOf(Movie(name = "A"), Movie(name = "B"))
                }

                override fun saveAllMovies(movies: Movies) {
                    super.saveAllMovies(movies)
                }
            }

            //act
            val result = getAllMovies(moviesGateway)

            //assert
            val expected = listOf(Movie(name = "A"), Movie(name = "B"))
            assertEquals(expected, result)
        }


    @Test(expected = FailedToRequestException::class)
    fun `getAllMovies() with failed requestAllMovies() then never invoke saveAllMovies()`() =
        runBlocking {
            //arrange
            var invoked = true

            val moviesGateway = object : MoviesGateway {
                override suspend fun requestAllMovies(): Movies {
                    return super.requestAllMovies()
                }

                override fun saveAllMovies(movies: Movies) {
                    invoked = false
                    super.saveAllMovies(movies)
                }
            }

            //act
            getAllMovies(moviesGateway)

            //assert
            assertFalse(invoked)

        }

    @Test(expected = FailedToRequestException::class)
    fun `getAllMovies() with fail requestAllMovies() then throw FailedToRequestException`() =
        runBlocking {
            //arrange
            var invoked = false

            val moviesGateway = object : MoviesGateway {
                override suspend fun requestAllMovies(): Movies {
                    return super.requestAllMovies()
                }

                override fun saveAllMovies(movies: Movies) {
                    invoked = true
                    super.saveAllMovies(movies)
                }
            }

            //act
            val result = getAllMovies(moviesGateway)

            //assert
        }

    @Test
    fun `sortMoviesByName() with non null movies list then return a sorted list by name`() {
        //arrange
        val movies = listOf(
            Movie(name = "Ahmed"),
            Movie(name = "Med"),
            Movie(name = "Mustapha"),
            Movie(name = "Idris"),
            Movie(name = "Salah")
        )

        //act
        val result = sortMoviesByName(movies)

        //assert
        val expected = listOf(
            Movie(name = "Ahmed"),
            Movie(name = "Idris"),
            Movie(name = "Med"),
            Movie(name = "Mustapha"),
            Movie(name = "Salah")
        )
        assertEquals(expected, result)

    }

    @Test
    fun `sortMoviesByName() with null movies list then return null`() {
        //arrange
        val movies: Movies? = null

        //act
        val result = sortMoviesByName(movies)

        //assert
        assertNull(result)

    }

    @Test
    fun `sortMoviesByRating() with non null movies list then return a sorted list by rating desc`() {
        //arrange
        val movies = listOf(
            Movie(rating = 1.0),
            Movie(rating = 1.6),
            Movie(rating = 1.8),
            Movie(rating = 1.2),
            Movie(rating = 0.5)
        )

        //act
        val result = sortMoviesByRating(movies)

        //assert
        val expected = listOf(
            Movie(rating = 1.8),
            Movie(rating = 1.6),
            Movie(rating = 1.2),
            Movie(rating = 1.0),
            Movie(rating = 0.5)
        )
        assertEquals(expected, result)

    }

    @Test(expected = UnsupportedOperationException::class)
    fun `sortMoviesByRating() with null movies list then return UnsupportedOperationException`() {
        //arrange
        val movies: Movies? = null
        //act
        val result = sortMoviesByRating(movies)
    }


}
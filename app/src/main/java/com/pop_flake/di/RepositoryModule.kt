package com.pop_flake.di

import com.pop_flake.network.remote.MovieService
import com.pop_flake.repository.HomeRepository
import com.pop_flake.repository.MovieDetailRepository
import com.pop_flake.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {


    @Provides
    fun providesHomeMovies(movieService: MovieService): HomeRepository {
        return HomeRepository(movieService)
    }

    @Provides
    fun providesMovieDetails(movieService: MovieService): MovieDetailRepository {
        return MovieDetailRepository(movieService)
    }

    @Provides
    fun providesSearchMovies(movieService: MovieService): SearchRepository {
        return SearchRepository(movieService)
    }

}
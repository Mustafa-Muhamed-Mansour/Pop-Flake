package com.pop_flake.di

import com.pop_flake.details.MovieDetailViewModel
import com.pop_flake.home.HomeViewModel
import com.pop_flake.network.remote.MovieService
import com.pop_flake.repository.HomeRepository
import com.pop_flake.repository.MovieDetailRepository
import com.pop_flake.repository.SearchRepository
import com.pop_flake.search.SearchViewModel
import com.pop_flake.ui.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun providesSplashMovies(): SplashViewModel {
        return SplashViewModel()
    }

    @Provides
    fun providesHomeMovies(homeRepository: HomeRepository): HomeViewModel {
        return HomeViewModel(homeRepository)
    }


    @Provides
    fun providesMovieDetails(movieDetailRepository: MovieDetailRepository): MovieDetailViewModel {
        return MovieDetailViewModel(movieDetailRepository)
    }


    @Provides
    fun providesSearchMovies(searchRepository: SearchRepository): SearchViewModel {
        return SearchViewModel(searchRepository)
    }

}
package br.com.brunoccbertolini.data.di

import br.com.brunoccbertolini.data.repository.MoviesRepositoryImpl
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindMoviesRepository(moviesRepository: MoviesRepositoryImpl): MoviesRepository
}
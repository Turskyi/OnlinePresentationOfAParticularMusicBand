package com.music.android.sensilence.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.repository.SongsRepositoryImpl;
import io.github.turskyi.domain.repository.SongsRepository;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    SongsRepository provideRepository(SongsRepositoryImpl repository) {
        return repository;
    }
}

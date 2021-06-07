package com.music.android.sensilence.domain.di.modules;

import com.music.android.sensilence.data.repository.SongsRepositoryImpl;
import com.music.android.sensilence.domain.repository.SongsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    SongsRepository provideRepository(SongsRepositoryImpl repository) {
        return repository;
    }
}

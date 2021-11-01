package com.music.android.sensilence.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.repository.SongsRepositoryImpl;
import io.github.turskyi.domain.repository.SongsRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    public abstract SongsRepository bindRepository(SongsRepositoryImpl repository);
}

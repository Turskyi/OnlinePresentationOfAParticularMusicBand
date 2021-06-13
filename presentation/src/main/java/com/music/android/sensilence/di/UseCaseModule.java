package com.music.android.sensilence.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.usecase.GetSongsUseCaseImpl;
import io.github.turskyi.domain.usecase.GetSongsUseCase;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    @Singleton
    GetSongsUseCase provideUseCase(GetSongsUseCaseImpl useCase){
        return useCase;
    }
}

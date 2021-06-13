package com.music.android.sensilence.presentation.di;

import com.music.android.sensilence.data.usecase.GetSongsUseCaseImpl;
import com.music.android.sensilence.domain.usecase.GetSongsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    @Singleton
    GetSongsUseCase provideUseCase(GetSongsUseCaseImpl useCase){
        return useCase;
    }
}

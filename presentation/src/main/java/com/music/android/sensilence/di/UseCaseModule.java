package com.music.android.sensilence.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.usecase.GetSongsUseCaseImpl;
import io.github.turskyi.domain.usecase.GetSongsUseCase;

@Module
@InstallIn(SingletonComponent.class)
public abstract class UseCaseModule {
    @Binds
    public abstract GetSongsUseCase bindUseCase(GetSongsUseCaseImpl useCase);
}

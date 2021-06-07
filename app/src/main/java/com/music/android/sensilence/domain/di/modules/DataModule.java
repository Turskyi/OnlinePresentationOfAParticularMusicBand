package com.music.android.sensilence.domain.di.modules;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module(includes = {
        RepositoryModule.class,
        UseCaseModule.class
})
@InstallIn(SingletonComponent.class)
public class DataModule {
}

package com.music.android.sensilence.di;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.database.SongDao;
import io.github.turskyi.data.database.SongsDatabase;

import static com.music.android.sensilence.BuildConfig.DATABASE_SONGS;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    AppModule() {
    }

    @Singleton
    @Provides
    SongsDatabase.Callback provideCallback(Application app){
        return new SongsDatabase.Callback(app);
    }

    @Provides
    @Singleton
    public SongsDatabase provideDatabase(Application app, SongsDatabase.Callback callback) {
        return Room.databaseBuilder(app, SongsDatabase.class, DATABASE_SONGS)
                .addCallback(callback)
                .build();
    }

    @Provides
    public SongDao provideSongDao(SongsDatabase database) {
        return database.getSongDao();
    }
}
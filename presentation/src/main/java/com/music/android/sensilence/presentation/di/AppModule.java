package com.music.android.sensilence.presentation.di;

import android.app.Application;

import androidx.room.Room;

import com.music.android.sensilence.data.database.SongDao;
import com.music.android.sensilence.data.database.SongsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static com.music.android.sensilence.BuildConfig.DATABASE_SONGS;

@Module
@InstallIn(SingletonComponent.class)
// Java program implementing AppModule class
// with getInstance() method
public class AppModule {

    AppModule() {
    }

    @Provides
    @Singleton
    public SongsDatabase provideDatabase(Application app, SongsDatabase.Callback callback) {
        return Room.databaseBuilder(app, SongsDatabase.class, DATABASE_SONGS)
                .addCallback(callback)
                //        TODO: remove before release
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public SongDao provideSongDao(SongsDatabase database) {
        return database.getSongDao();
    }
}
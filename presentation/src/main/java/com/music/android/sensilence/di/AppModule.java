package com.music.android.sensilence.di;

import static com.music.android.sensilence.BuildConfig.DATABASE_SONGS;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.database.SongDao;
import io.github.turskyi.data.database.SongsDatabase;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    AppModule() {
    }

    @Singleton
    @Provides
    SongsDatabase.Callback provideDatabaseCallback(Application app) {
        return new SongsDatabase.Callback(app);
    }

    @Provides
    @Singleton
    public SongsDatabase provideDatabase(Application app, SongsDatabase.Callback callback) {
        return Room.databaseBuilder(app, SongsDatabase.class, DATABASE_SONGS)
                .addCallback(callback)
                .fallbackToDestructiveMigrationFrom(2)
                .build();
    }

    @Provides
    public SongDao provideSongDao(SongsDatabase database) {
        return database.getSongDao();
    }

    @Provides
    public CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }
}
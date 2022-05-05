package com.music.android.sensilence.di;

import android.app.Application;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.github.turskyi.data.database.SongDao;
import io.github.turskyi.data.database.SongsDatabase;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import javax.inject.Singleton;

import static com.music.android.sensilence.BuildConfig.DATABASE_SONGS;

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
                /*
                 * If you don’t want to provide migrations,
                 * and you specifically want your database to be cleared
                 * when you upgrade the version,
                 * call fallbackToDestructiveMigration in the database builder:
                 * */
                .fallbackToDestructiveMigration()
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
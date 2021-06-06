package com.music.android.sensilence.presentation.di;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.music.android.sensilence.data.database.SongsDatabase;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static com.music.android.sensilence.BuildConfig.DATABASE_SONGS;

@Module
@InstallIn(SingletonComponent.class)
// Java program implementing AppModule class
// with getInstance() method
public class AppModule {
    // static variable instance of type AppModule
    private static AppModule instance = null;

    // private constructor restricted to this class itself
    private AppModule() {

    }

    // static method to create instance of AppModule class
    public static AppModule getInstance() {
        if (instance == null) {
            instance = new AppModule();
        }
        return instance;
    }

    public SongsDatabase provideDatabase(Application app, RoomDatabase.Callback callback) {
        return Room.databaseBuilder(app, SongsDatabase.class, DATABASE_SONGS)
//        TODO: remove before release
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build();
    }
}
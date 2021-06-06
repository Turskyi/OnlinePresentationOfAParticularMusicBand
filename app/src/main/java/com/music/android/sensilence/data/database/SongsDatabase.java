package com.music.android.sensilence.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.music.android.sensilence.data.entity.SongEntity;

@Database(entities = {SongEntity.class},version = 1)
public abstract class SongsDatabase extends RoomDatabase {
    public abstract SongDao getSongDao();

}

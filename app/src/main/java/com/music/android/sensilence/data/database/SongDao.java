package com.music.android.sensilence.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.music.android.sensilence.data.entity.SongEntity;

import java.util.List;

import io.reactivex.Single;

import static com.music.android.sensilence.data.entity.SongEntity.COLUMN_ALBUM;
import static com.music.android.sensilence.data.entity.SongEntity.TABLE_SONGS;

@Dao
public interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSong(SongEntity item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSongs(List<SongEntity> songs);

    @Query("SELECT * FROM " + TABLE_SONGS + " WHERE " + COLUMN_ALBUM + " = :album")
    Single<List<SongEntity>> getAlbumSongs(String album);
}

package io.github.turskyi.data.database;

import static io.github.turskyi.data.entity.SongEntity.COLUMN_ALBUM;
import static io.github.turskyi.data.entity.SongEntity.TABLE_SONGS;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.github.turskyi.data.entity.SongEntity;
import io.reactivex.Single;

@Dao
public interface SongDao {

    @Query("SELECT * FROM " + TABLE_SONGS + " WHERE " + COLUMN_ALBUM + " = :album")
    Single<List<SongEntity>> getAlbumSongs(String album);
}

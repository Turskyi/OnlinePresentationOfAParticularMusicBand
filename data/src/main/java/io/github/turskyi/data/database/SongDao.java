package io.github.turskyi.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.github.turskyi.data.entity.SongEntity;
import io.reactivex.Single;

import static io.github.turskyi.data.entity.SongEntity.COLUMN_ALBUM;
import static io.github.turskyi.data.entity.SongEntity.TABLE_SONGS;

@Dao
public interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SongEntity item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSongs(List<SongEntity> songs);

    @Query("SELECT * FROM " + TABLE_SONGS + " WHERE " + COLUMN_ALBUM + " = :album")
    Single<List<SongEntity>> getAlbumSongs(String album);
}

package com.music.android.sensilence.domain.repository;

import com.music.android.sensilence.data.entity.SongEntity;

import java.util.List;

import io.reactivex.Single;

public interface SongsRepository {
    Single<List<SongEntity>> getSongsFromAlbum(String album);
}

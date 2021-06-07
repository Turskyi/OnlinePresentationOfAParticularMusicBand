package com.music.android.sensilence.data.repository;

import com.music.android.sensilence.data.database.SongDao;
import com.music.android.sensilence.data.entity.SongEntity;
import com.music.android.sensilence.domain.repository.SongsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class SongsRepositoryImpl implements SongsRepository {
    private final SongDao dao;

    @Inject
    SongsRepositoryImpl(SongDao dao){
        this.dao = dao;
    }

    @Override
    public Single<List<SongEntity>> getSongsFromAlbum(String album) {
        return dao.getAlbumSongs(album).map((songs)-> songs);
    }
}

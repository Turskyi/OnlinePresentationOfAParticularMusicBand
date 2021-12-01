package io.github.turskyi.data.repository;

import hu.akarnokd.rxjava3.bridge.RxJavaBridge;
import io.github.turskyi.data.database.SongDao;
import io.github.turskyi.domain.entities.pojo.Song;
import io.github.turskyi.domain.repository.SongsRepository;
import io.reactivex.rxjava3.core.Single;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SongsRepositoryImpl implements SongsRepository {
    private final SongDao dao;

    /* Constructor-injected, because Hilt needs to know how to
     * provide instances of  SongsRepositoryImpl, too. */
    @Inject
    SongsRepositoryImpl(SongDao dao) {
        this.dao = dao;
    }

    @Override
    public Single<List<Song>> getSongsFromAlbum(String album) {
        return RxJavaBridge.toV3Single(dao.getAlbumSongs(album).map((entities) -> {
            List<Song> songs = new ArrayList<>();
            for (io.github.turskyi.data.entity.SongEntity entity : entities) {
                Song song = entity.mapToDomain ( );
                songs.add (song);
            }
            return songs;
        }));
    }
}

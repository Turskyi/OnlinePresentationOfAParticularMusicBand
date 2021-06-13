package io.github.turskyi.domain.repository;

import java.util.List;

import io.github.turskyi.domain.entities.pojo.Song;
import io.reactivex.Single;

public interface SongsRepository {
    Single<List<Song>> getSongsFromAlbum(String album);
}

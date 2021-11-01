package io.github.turskyi.domain.usecase;

import java.util.List;

import io.github.turskyi.domain.entities.pojo.Song;

public interface GetSongsUseCase {

    io.reactivex.rxjava3.disposables.Disposable setSongs(
            String album,
            io.reactivex.rxjava3.functions.Consumer<List<Song>> successConsumer,
            io.reactivex.rxjava3.functions.Consumer<String> errorConsumer
    );
}

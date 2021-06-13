package io.github.turskyi.domain.usecase;

import java.util.List;

import io.github.turskyi.domain.entities.pojo.Song;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface GetSongsUseCase {

    Disposable getDisposableSongs(
            String album,
            Consumer<List<Song>> successConsumer,
            Consumer<String> errorConsumer
    );
}
